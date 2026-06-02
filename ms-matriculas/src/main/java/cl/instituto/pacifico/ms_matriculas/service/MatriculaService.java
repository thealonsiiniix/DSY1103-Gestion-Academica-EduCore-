package cl.instituto.pacifico.ms_matriculas.service;
import cl.instituto.pacifico.ms_matriculas.dto.CarreraDTO;
import cl.instituto.pacifico.ms_matriculas.dto.EstudianteDTO;
import cl.instituto.pacifico.ms_matriculas.exception.BusinessException;
import cl.instituto.pacifico.ms_matriculas.exception.ResourceNotFoundException;
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
    private static final Logger log =
            LoggerFactory.getLogger(MatriculaService.class);
    private final MatriculaRepository repository;
    public MatriculaService(MatriculaRepository repository) {
        this.repository = repository;
    }

    private final WebClient estudianteClient = WebClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeaders(headers ->
                    headers.setBasicAuth("admin", "1234"))
            .build();

    private final WebClient academicoClient = WebClient.builder()
            .baseUrl("http://localhost:8083")
            .defaultHeaders(headers ->
                    headers.setBasicAuth("admin", "1234"))
            .build();

    public Matricula crear(Matricula matricula) {
        log.info("Iniciando creación de matrícula");
        log.info("Validando estudiante ID: {}",
                matricula.getEstudianteId());
        EstudianteDTO estudiante = estudianteClient.get()
                .uri("/api/v1/estudiantes/" + matricula.getEstudianteId())
                .retrieve()
                .bodyToMono(EstudianteDTO.class)
                .block();
        if (estudiante == null) {
            log.warn("Estudiante no encontrado");
            throw new BusinessException(
                    "El estudiante indicado no existe");
        }

        log.info("Estudiante validado correctamente");
        log.info("Validando carrera ID: {}",
                matricula.getCarreraId());
        CarreraDTO carrera = academicoClient.get()
                .uri("/api/v1/academico/" + matricula.getCarreraId())
                .retrieve()
                .bodyToMono(CarreraDTO.class)
                .block();
        if (carrera == null) {
            log.warn("Carrera no encontrada");
            throw new BusinessException(
                    "La carrera indicada no existe");
        }
        log.info("Carrera validada correctamente");
        matricula.setFechaMatricula(LocalDate.now());
        if (matricula.getEstado() == null) {
            matricula.setEstado("ACTIVA");
        }
        Matricula nueva = repository.save(matricula);
        log.info("Matrícula creada correctamente con ID: {}", nueva.getId());
        return nueva;
    }

    public List<Matricula> listar() {
        log.info("Listando matrículas");
        return repository.findAll();
    }

    public Matricula obtener(Long id) {
        log.info("Buscando matrícula con ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Matrícula no encontrada con ID: {}", id);
                    return new ResourceNotFoundException("Matrícula no encontrada con ID: " + id);
                });
    }

    public Matricula actualizar(Long id, Matricula matriculaActualizada) {
        log.info("Actualizando matrícula con ID: {}", id);
        Matricula matricula = obtener(id);
        CarreraDTO carrera = academicoClient.get()
                .uri("/api/v1/academico/" +
                        matriculaActualizada.getCarreraId())
                .retrieve()
                .bodyToMono(CarreraDTO.class)
                .block();
        if (carrera == null) {
            log.warn("La carrera indicada no existe");
            throw new BusinessException("La carrera indicada no existe");
        }
        matricula.setCarreraId(matriculaActualizada.getCarreraId());
        matricula.setSeccion(matriculaActualizada.getSeccion());
        matricula.setEstado(matriculaActualizada.getEstado());
        Matricula actualizada = repository.save(matricula);
        log.info("Matrícula actualizada correctamente");
        return actualizada;
    }

    public void eliminar(Long id) {
        log.info("Solicitando eliminación de matrícula ID: {}", id);
        Matricula matricula = obtener(id);
        repository.delete(matricula);
        log.info("Matrícula eliminada correctamente");
    }
}