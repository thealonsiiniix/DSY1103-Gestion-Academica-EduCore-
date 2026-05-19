package cl.instituto.pacifico.ms_matriculas.service;
import cl.instituto.pacifico.ms_matriculas.dto.CarreraDTO;
import cl.instituto.pacifico.ms_matriculas.dto.EstudianteDTO;
import cl.instituto.pacifico.ms_matriculas.model.Matricula;
import cl.instituto.pacifico.ms_matriculas.repository.MatriculaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDate;
import java.util.List;

@Service
public class MatriculaService {
    private static final Logger log = LoggerFactory.getLogger(MatriculaService.class);
    private final MatriculaRepository repository;
    public MatriculaService(MatriculaRepository repository) {
        this.repository = repository;
    }

    // MS estudiantes
    private final WebClient estudianteClient = WebClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeaders(headers ->
                    headers.setBasicAuth("admin", "1234"))
            .build();

    // MS academico
    private final WebClient academicoClient = WebClient.builder()
            .baseUrl("http://localhost:8083")
            .defaultHeaders(headers ->
                    headers.setBasicAuth("admin", "1234"))
            .build();

    // CREAR
    public Matricula crear(Matricula matricula) {
        log.info("Creando matrícula");
        // validar estudiante
        log.info("Consultando ms-estudiantes");
        EstudianteDTO estudiante = estudianteClient.get()
                .uri("/api/v1/estudiantes/" + matricula.getEstudianteId())
                .retrieve()
                .bodyToMono(EstudianteDTO.class)
                .block();
        if (estudiante == null) {
            log.error("El estudiante no existe");
            throw new RuntimeException("El estudiante no existe");
        }
        // validar carrera
        log.info("Consultando ms-academico");
        CarreraDTO carrera = academicoClient.get()
                .uri("/api/v1/academico/" + matricula.getCarreraId())
                .retrieve()
                .bodyToMono(CarreraDTO.class)
                .block();
        if (carrera == null) {
            log.error("La carrera no existe");
            throw new RuntimeException("La carrera no existe");
        }
        matricula.setFechaMatricula(LocalDate.now());
        if (matricula.getEstado() == null) {
            matricula.setEstado("ACTIVA");
        }
        log.info("Matricula creada correctamente");
        return repository.save(matricula);
    }

    // LISTAR
    public List<Matricula> listar() {
        log.info("Listando matriculas");
        return repository.findAll();
    }

    // BUSCAR POR ID
    public Matricula obtener(Long id) {
        log.info("Buscando matricula con ID: {}", id);
        return repository.findById(id).orElse(null);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        log.info("Eliminando matricula con ID: {}", id);
        repository.deleteById(id);
        log.info("Matricula eliminada correctamente");
    }

    // ACTUALIZAR
    public Matricula actualizar(Long id, Matricula matriculaActualizada) {
        log.info("Actualizando matricula con ID: {}", id);
        Matricula matricula = repository.findById(id).orElse(null);
        if (matricula == null) {
            log.error("Matricula no encontrada");
            return null;
        }
        // validar carrera
        CarreraDTO carrera = academicoClient.get()
                .uri("/api/v1/academico/" + matriculaActualizada.getCarreraId())
                .retrieve()
                .bodyToMono(CarreraDTO.class)
                .block();
        if (carrera == null) {
            throw new RuntimeException("La carrera no existe");
        }
        matricula.setCarreraId(matriculaActualizada.getCarreraId());
        matricula.setSeccion(matriculaActualizada.getSeccion());
        matricula.setEstado(matriculaActualizada.getEstado());
        log.info("Matricula actualizada correctamente");
        return repository.save(matricula);
    }
}