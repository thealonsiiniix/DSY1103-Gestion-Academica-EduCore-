package cl.instituto.pacifico.ms_evaluacion.service;
import cl.instituto.pacifico.ms_evaluacion.dto.MatriculaDTO;
import cl.instituto.pacifico.ms_evaluacion.dto.ResultadoDTO;
import cl.instituto.pacifico.ms_evaluacion.model.Evaluacion;
import cl.instituto.pacifico.ms_evaluacion.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class EvaluacionService {
    private final EvaluacionRepository repository;
    public EvaluacionService(EvaluacionRepository repository) {
        this.repository = repository;
    }

    // conexión ms-matriculas
    private final WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8084")
            .defaultHeaders(headers ->
                    headers.setBasicAuth("admin", "1234"))
            .build();

    // Crear evaluación
    public Evaluacion crear(Evaluacion evaluacion) {
        MatriculaDTO matricula = client.get()
                .uri("/api/matriculas/" + evaluacion.getMatriculaId())
                .retrieve()
                .bodyToMono(MatriculaDTO.class)
                .block();
        if (matricula == null) {
            throw new RuntimeException("La matrícula no existe");
        }
        return repository.save(evaluacion);
    }

    // Listar
    public List<Evaluacion> listar() {
        return repository.findAll();
    }

    // Buscar por ID
    public Evaluacion buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Eliminar
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    // Actualizar
    public Evaluacion actualizar(Long id, Evaluacion nueva) {
        Evaluacion evaluacion = buscarPorId(id);
        if (evaluacion == null) {
            return null;
        }
        evaluacion.setMatriculaId(nueva.getMatriculaId());
        evaluacion.setNota(nueva.getNota());
        return repository.save(evaluacion);
    }

    // Resultado final
    public ResultadoDTO calcularResultado(Long matriculaId) {
        List<Evaluacion> notas = repository.findByMatriculaId(matriculaId);
        if (notas.isEmpty()) {
            throw new RuntimeException("No existen evaluaciones");
        }
        double promedio = notas.stream()
                .mapToDouble(Evaluacion::getNota)
                .average()
                .orElse(0.0);
        String estado;
        if (promedio >= 4.0) {
            estado = "APROBADO";
        } else if (promedio >= 3.5) {
            estado = "EXAMEN";
        } else {
            estado = "REPROBADO";
        }
        return new ResultadoDTO(matriculaId, promedio, estado);
    }
}
