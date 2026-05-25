package cl.instituto.pacifico.ms_practicas.service;

import cl.instituto.pacifico.ms_practicas.controller.PracticaController;
import cl.instituto.pacifico.ms_practicas.dto.ArancelDTO;
import cl.instituto.pacifico.ms_practicas.dto.EmpresaDTO;
import cl.instituto.pacifico.ms_practicas.dto.EstudianteDTO;
import cl.instituto.pacifico.ms_practicas.model.Practica;
import cl.instituto.pacifico.ms_practicas.repository.PracticaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
                .bodyToMono(EstudianteDTO.class)// cambia de json a obj
                .block(); // detiene el flujo hasta recibir una respuesta
        // Validar que exista
        if (e == null) {
            log.error("El estudiante no existe");
            throw new RuntimeException("Estudiante no existe");
        }

        // Obtener empresa
        log.info("Consultando ms-empresa");
        EmpresaDTO emp = clientEmpresa.get()
                .uri("/api/v1/empresas/" + practica.getIdEmpresa())
                .retrieve()
                .bodyToMono(EmpresaDTO.class)
                .block();

        if (emp == null) {
            log.error("La empresa no existe");
            throw new RuntimeException("Empresa no existe");
        }

        // Obtiene arancel de MS finanzas
        log.info("Consultando ms-finanzas");
        ArancelDTO arancel = clientFinanzas.get()
                .uri("/api/v1/aranceles/rut/" +  practica.getRutEstudiante())
                .retrieve()
                .bodyToMono(ArancelDTO.class)
                .block();

        if (arancel == null) {
            log.error("El arancel no existe");
            throw new RuntimeException("No se encontró información financiera");
        }

        if (!arancel.getEstado().equalsIgnoreCase("pagado")) {
            log.error("El estudiante tiene deuodas");
            throw new RuntimeException("El estudiante tiene deudas, no puede inscribir su practica");
        }

        // Copia datos al modelo practica
        practica.setRutEstudiante(e.getRut());
        if (practicaRepository.existsByRutEstudiante(practica.getRutEstudiante())) {
            log.info("Estudiamte tiene una práctica registrada");
            throw new RuntimeException("El estudiante ya tiene una práctica registrada");
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
        Optional<Practica> practica = practicaRepository.findById(id);
        return practica.orElse(null);
    }

    // OBTENER PRACTICA POR RUT ESTUDIANTE
    public List<Practica> obtenerPorRut(String rutEstudiante){
        log.info("Buscando Practicas con Rut: {}", rutEstudiante);
        return practicaRepository.findByRutEstudiante(rutEstudiante);
    }

    // SI EXISTE X ID
    public boolean existePorId(Long id){
        return practicaRepository.existsById(id);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        log.info("Eliminando Practicas con ID: {}", id);
        practicaRepository.deleteById(id);
        log.info("Practicas eliminada correctamente");

    }


    // ACTUALIZAR LOS DATOS DE LA PRACTICA
    public Optional<Practica> actualizarCompleta(Long id, Practica practicaActualizada){

        // VALIDAR SI EXISTE LA PRACTICA
        Optional<Practica> practicaOptional = practicaRepository.findById(id);

        if (practicaOptional.isEmpty()) {
            log.error("La practica con ID {} no existe", id);
            throw new RuntimeException("Id practica no existe");
        }

        // BUSCAR EMPRESA
        EmpresaDTO emp = clientEmpresa.get()
                .uri("/api/v1/empresas/" + practicaActualizada.getIdEmpresa())
                .retrieve()
                .bodyToMono(EmpresaDTO.class)
                .block();

        // VALIDAR EMPRESA
        if (emp == null) {
            log.error("La empresa no puede ser null");
            throw new RuntimeException("Empresa no puede ser null");
        }

        // OBTENER PRACTICA
        Practica practica = practicaOptional.get();

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
        return Optional.of(practicaRepository.save(practica));
    }
}
