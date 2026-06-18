package cl.instituto.pacifico.ms_asistencia.service;

import cl.instituto.pacifico.ms_asistencia.dto.EstudianteDTO;
import cl.instituto.pacifico.ms_asistencia.exception.BusinessException;
import cl.instituto.pacifico.ms_asistencia.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_asistencia.model.Asistencia;
import cl.instituto.pacifico.ms_asistencia.repository.AsistenciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {
    private static final Logger log = LoggerFactory.getLogger(AsistenciaService.class);
    // Variable de el repositorio
    public final AsistenciaRepository asistenciaRepository;

    // inyecta el repositorio en el contructor
    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    // DEFINIMOS LA DIRECCION DEL MICROSERVVICIO QUE USAREMOS EN LA VARIABLE CLIENT
    private final WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeaders(headers ->
            headers.setBasicAuth("admin", "1234"))
            .build();

    // CREAR ASISTENCIA
    public Asistencia crear(Asistencia asistencia){
        log.info("Creando Asistencia");
        log.info("Consultando ms-estudiantes");

        // peticion GET al MS de producto
        EstudianteDTO e = client.get()
                .uri("/api/v1/estudiantes/rut/"+asistencia.getRutEstudiante()) // endpoint al que llama
                .retrieve() // ejecuta la llamada
                .onStatus(status -> status.is4xxClientError(),
                        response -> {
                            log.warn("Estudiante no existe{}", asistencia.getEstudianteId());
                            return Mono.error(new BusinessException("El estudiante indicado no existe"));
                        })
                .onStatus(status -> status.is5xxServerError(),
                        response -> {
                            log.error("Error en ms-estudiantes");
                            return Mono.error(new BusinessException("Error en servicio de estudiantes"));
                        })
                .bodyToMono(EstudianteDTO.class)// cambia de json a obj
                .block(); // detiene el flujo hasta recibir una respuesta

        //  Copiar datos al modelo Asistencia
        asistencia.setRutEstudiante(e.getRut());
        asistencia.setEstudianteId(e.getId());
        asistencia.setNombreEstudiante(e.getNombre());
        asistencia.setFechaAsistencia(LocalDate.now());

        // GUARDAR
        log.info("Asistencia creada correctamente");
        return asistenciaRepository.save(asistencia);
    }

    // METODO LISTA TOD0
    public List<Asistencia> listar() {
        log.info("Listando Asistencias");
        return asistenciaRepository.findAll();
    }

    // BUSCAR POR ID
    public Asistencia buscarPorId(Long id) {
        log.info("Buscando Asistencia con ID: {}", id);
        return asistenciaRepository.findById(id).orElseThrow(() -> {
                    log.warn("Asistencia no encontrada con ID: {}", id);
                    return new  ResourceNotFoundException("Asistencia con id " + id + " no encontrada");
                });
    }

    // OBTENER ASISTTENCIA POR RUT ESTUDIANTE
    public List<Asistencia> obtenerPorRut(String rutEstudiante) {
        log.info("Buscando Asistencias con Rut: {}", rutEstudiante);

        List<Asistencia> lista = asistenciaRepository.findByRutEstudiante(rutEstudiante);

        if (lista.isEmpty()) {
            throw new ResourceNotFoundException("No existen asistencias para el rut " + rutEstudiante);
        }

        return lista;
    }

    // ELIMINAR
    public void eliminar(Long id) {
        log.info("Eliminando Asistencia con ID: {}", id);

        if (!asistenciaRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Asistencia con id " + id + " no existe");
        }

        asistenciaRepository.deleteById(id);
        log.info("Asistencia eliminada correctamente");
    }

    // ACTUALIZAR LOS DATOS DE LA ASISTENCIA
    public Asistencia actualizarCompleta(Long id, Asistencia asistenciaActualizada) {

        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Asistencia con id " + id + " no encontrada"
                ));

        EstudianteDTO e = client.get()
                .uri("/api/v1/estudiantes/rut/" + asistenciaActualizada.getRutEstudiante())
                .retrieve()
                .bodyToMono(EstudianteDTO.class)
                .block();

        if (e == null) {
            log.warn("La asistencia indicada no existe");
            throw new BusinessException("El estudiante no existe");
        }

        asistencia.setRutEstudiante(e.getRut());
        asistencia.setEstudianteId(e.getId());
        asistencia.setNombreEstudiante(e.getNombre());

        return asistenciaRepository.save(asistencia);
    }
}
