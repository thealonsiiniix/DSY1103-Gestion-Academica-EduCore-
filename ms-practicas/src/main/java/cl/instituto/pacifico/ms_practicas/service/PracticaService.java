package cl.instituto.pacifico.ms_practicas.service;

import cl.instituto.pacifico.ms_practicas.controller.PracticaController;
import cl.instituto.pacifico.ms_practicas.dto.ArancelDTO;
import cl.instituto.pacifico.ms_practicas.dto.EmpresaDTO;
import cl.instituto.pacifico.ms_practicas.dto.EstudianteDTO;
import cl.instituto.pacifico.ms_practicas.exception.BusinessException;
import cl.instituto.pacifico.ms_practicas.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_practicas.model.Practica;
import cl.instituto.pacifico.ms_practicas.repository.PracticaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PracticaService {
    // Variable de el repositorio
    private static final Logger log = LoggerFactory.getLogger(PracticaService.class);

    public final PracticaRepository practicaRepository;

    // inyecta el repositorio en el contructor
    public PracticaService(PracticaRepository practicaRepository) {
        this.practicaRepository = practicaRepository;
    }

    private final WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeaders(headers ->
            headers.setBasicAuth("admin", "1234"))
            .build();

    private final WebClient clientEmpresa = WebClient.builder()
            .baseUrl("http://localhost:8089")
            .defaultHeaders(headers ->
            headers.setBasicAuth("admin", "1234"))
            .build();

    private final WebClient clientFinanzas = WebClient.builder()
            .baseUrl("http://localhost:8087")
            .defaultHeaders(headers ->
            headers.setBasicAuth("admin", "1234"))
            .build();

    // CREAR PRACTICA
    public Practica crear(Practica practica){
        log.info("Creando Practica");
        log.info("Consultando ms-estudiantes");
        // Peticion GET al MS 8081 estudiante
        EstudianteDTO e = client.get()
                .uri("/api/v1/estudiantes/rut/"+practica.getRutEstudiante()) // endpoint al que llama
                .retrieve() // ejecuta la llamada
                .onStatus(status -> status.is4xxClientError(),
                        response -> {
                            log.warn("Estudiante no existe");
                            return Mono.error(new BusinessException("El estudiante indicado no existe"));
                        })
                .onStatus(status -> status.is5xxServerError(),
                        response -> {
                            log.error("Error en ms-estudiantes");
                            return Mono.error(new BusinessException("Error en servicio de estudiantes"));
                        })
                .bodyToMono(EstudianteDTO.class)// cambia de json a obj
                .block(); // detiene el flujo hasta recibir una respuesta

        // Obtener empresa
        log.info("Consultando ms-empresa");
        EmpresaDTO emp = clientEmpresa.get()
                .uri("/api/v1/empresas/" + practica.getIdEmpresa())
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> {
                            log.warn("la empresa no existe");
                            return Mono.error(new BusinessException("La empresa indicada no existe"));
                        })
                .onStatus(status -> status.is5xxServerError(),
                        response -> {
                            log.error("Error en ms-estudiantes");
                            return Mono.error(new BusinessException("Error en servicio empresa"));
                        })
                .bodyToMono(EmpresaDTO.class)
                .block();

        // Obtiene arancel de MS finanzas
        log.info("Consultando ms-finanzas");
        ArancelDTO arancel = clientFinanzas.get()
                .uri("/api/v1/aranceles/rut/" +  practica.getRutEstudiante())
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> {
                            log.warn("No se encontro informacion financiera");
                            return Mono.error(new BusinessException("La carrera indicada no existe"));
                        })
                .onStatus(status -> status.is5xxServerError(),
                        response -> {
                            log.error("Error en ms-academico");
                            return Mono.error(new BusinessException("Error en servicio de finanzas"));
                        })
                .bodyToMono(ArancelDTO.class)
                .block();

        if (!arancel.getEstado().equalsIgnoreCase("pagado")) {
            log.error("El estudiante tiene deuodas");
            throw new BusinessException("El estudiante tiene deudas, no puede inscribir su practica");
        }

        // Copia datos al modelo practica
        practica.setRutEstudiante(e.getRut());
        if (practicaRepository.existsByRutEstudiante(practica.getRutEstudiante())) {
            log.info("El estudiamte tiene una práctica registrada");
            throw new BusinessException("El estudiante ya tiene una práctica registrada");
        }

        practica.setEstudianteId(e.getId());
        practica.setNombreEstudiante(e.getNombre());
        practica.setEmailEstudiante(e.getEmail());
        practica.setTelefonoEstudiante(e.getTelefono());
        practica.setNombreEmpresa(emp.getNombre());
        practica.setRutEmpresa(emp.getRut());
        practica.setDireccionEmpresa(emp.getDireccion());
        practica.setTelefonoEmpresa(emp.getTelefono());
        practica.setEmailEmpresa(emp.getEmail());
        practica.setConvenioVigenteEmpresa(emp.getConvenioVigente());
        practica.setEstadoArancel(arancel.getEstado());

        // GUARDAR
        log.info("Practica creada correctamente");
        return practicaRepository.save(practica);
    }

    // METODO LISTA TOD0
    public List<Practica> listar() {
        log.info("Listando Practicas");
        return practicaRepository.findAll();
    }

    // BUSCAR POR ID
    public Practica buscarPorId(Long id) {
        log.info("Buscando Practicas con ID: {}", id);
        return practicaRepository.findById(id).orElseThrow(() -> {
                    log.warn("Practica no encontrada con ID: {}", id);
                    return new ResourceNotFoundException("Practica con id " + id + " no encontrada");
                });
    }

    // OBTENER PRACTICA POR RUT ESTUDIANTE
    public List<Practica> obtenerPorRut(String rutEstudiante){
        log.info("Buscando Practicas con Rut: {}", rutEstudiante);

        List<Practica> lista = practicaRepository.findByRutEstudiante(rutEstudiante);

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException("No existen practica con este rut " + rutEstudiante);
        }

        return lista;
    }

    // SI EXISTE X ID
    public boolean existePorId(Long id){
        return practicaRepository.existsById(id);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        log.info("Eliminando Practica con ID: {}", id);
        if (!practicaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Practica con ID: " + id + " no existe");
        }

        practicaRepository.deleteById(id);
        log.info("Practica eliminada correctamente");
    }

    // ACTUALIZAR LOS DATOS DE LA PRACTICA
    public Practica actualizarCompleta(Long id, Practica practicaActualizada){

        // VALIDAR SI EXISTE LA PRACTICA
        Practica practica = practicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Practica con id " + id + " no existe"));

        // BUSCAR EMPRESA
        log.info("Consultando ms-empresa");
        EmpresaDTO emp = clientEmpresa.get()
                .uri("/api/v1/empresas/" + practicaActualizada.getIdEmpresa())
                .retrieve()
                .onStatus(status -> status.is4xxClientError(),
                        response -> {
                            log.warn("la empresa no existe");
                            return Mono.error(new BusinessException("La empresa indicada no existe"));
                        })
                .onStatus(status -> status.is5xxServerError(),
                        response -> {
                            log.error("Error en ms-estudiantes");
                            return Mono.error(new BusinessException("Error en servicio empresa"));
                        })
                .bodyToMono(EmpresaDTO.class)
                .block();

        // ACTUALIZAR EMPRESA
        practica.setIdEmpresa(emp.getId());

        // ACTUALIZAR DATOS EMPRESA
        practica.setNombreEmpresa(emp.getNombre());
        practica.setRutEmpresa(emp.getRut());
        practica.setDireccionEmpresa(emp.getDireccion());
        practica.setTelefonoEmpresa(emp.getTelefono());
        practica.setEmailEmpresa(emp.getEmail());
        practica.setConvenioVigenteEmpresa(emp.getConvenioVigente());

        // GUARDAR
        log.info("Practica actualizada correctamente");
        return practicaRepository.save(practica);
    }
}
