package cl.instituto.pacifico.ms_matriculas.service;
import cl.instituto.pacifico.ms_matriculas.dto.EstudianteDTO;
import cl.instituto.pacifico.ms_matriculas.model.Matricula;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaService {
    private final List<Matricula> lista = new ArrayList<>();
    private Long contador = 1L;

    // conexion a ms-estudiantes
    private final WebClient client = WebClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeaders(headers ->
                    headers.setBasicAuth("admin", "1234"))
            .build();

    // Crear matrícula
    public Matricula crear(Matricula matricula) {
        // Validar estudiante en el otro ms
        EstudianteDTO estudiante = client.get()
                .uri("/api/estudiantes/" + matricula.getEstudianteId())
                .retrieve()
                .bodyToMono(EstudianteDTO.class)
                .block();
        if (estudiante == null) {
            throw new RuntimeException("Estudiante no existe");
        }
        matricula.setId(contador++);
        lista.add(matricula);
        return matricula;
    }

    // Listar
    public List<Matricula> listar() {
        return lista;
    }

    // Buscar por ID
    public Matricula obtener(Long id) {
        return lista.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
