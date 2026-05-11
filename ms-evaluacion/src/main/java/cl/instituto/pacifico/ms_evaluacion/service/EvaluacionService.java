package cl.instituto.pacifico.ms_evaluacion.service;
import cl.instituto.pacifico.ms_evaluacion.dto.MatriculaDTO;
import cl.instituto.pacifico.ms_evaluacion.dto.ResultadoDTO;
import cl.instituto.pacifico.ms_evaluacion.model.Evaluacion;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluacionService {
    private final List<Evaluacion> lista = new ArrayList<>();
    private Long contador = 1L;

    // conexión a ms-matriculas
    private final WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8084")
            .defaultHeaders(headers ->
                    headers.setBasicAuth("admin", "1234"))
            .build();

    // Crear evaluación
    public Evaluacion crear(Evaluacion evaluacion) {
        // Validar matrícula
        MatriculaDTO matricula;

        matricula = client.get()
                .uri("/api/matriculas/" + evaluacion.getMatriculaId())
                .retrieve()
                .bodyToMono(MatriculaDTO.class)
                .block();
        evaluacion.setId(contador++);
        lista.add(evaluacion);
        return evaluacion;
    }

    // Calcular promedio + estado (R3)
    public ResultadoDTO calcularResultado(Long matriculaId) {
        List<Evaluacion> notas = lista.stream()
                .filter(e -> e.getMatriculaId().equals(matriculaId))
                .collect(Collectors.toList());
        if (notas.isEmpty()) {
            throw new RuntimeException("No hay evaluaciones");
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

    public List<Evaluacion> listar() {
        return lista;
    }
}
