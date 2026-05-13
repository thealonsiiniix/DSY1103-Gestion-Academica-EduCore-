package cl.instituto.pacifico.ms_matriculas.service;
import cl.instituto.pacifico.ms_matriculas.dto.CarreraDTO;
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

    // Crear matrícula
    public Matricula crear(Matricula matricula) {
        // Validar estudiante
        EstudianteDTO estudiante = estudianteClient.get()
                .uri("/api/v1/estudiantes/" + matricula.getEstudianteId())
                .retrieve()
                .bodyToMono(EstudianteDTO.class)
                .block();
        if (estudiante == null) {
            throw new RuntimeException("El estudiante no existe");
        }

        // Validar carrera
        CarreraDTO carrera = academicoClient.get()
                .uri("/api/academico/" + matricula.getCarreraId())
                .retrieve()
                .bodyToMono(CarreraDTO.class)
                .block();
        if (carrera == null) {
            throw new RuntimeException("La carrera no existe");
        }
        matricula.setId(contador);
        contador++;
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

    // Eliminar
    public void eliminar(Long id) {
        lista.removeIf(m -> m.getId().equals(id));
    }

    // Actualizar
    public Matricula actualizar(Long id, Matricula matriculaActualizada) {
        Matricula matricula = obtener(id);
        if (matricula == null) {
            return null;
        }
        matricula.setCarreraId(matriculaActualizada.getCarreraId());
        matricula.setSeccion(matriculaActualizada.getSeccion());
        matricula.setFechaMatricula(matriculaActualizada.getFechaMatricula());
        matricula.setEstado(matriculaActualizada.getEstado());
        return matricula;
    }
}
