package cl.instituto.pacifico.ms_titulacion.service;
import cl.instituto.pacifico.ms_titulacion.dto.EvaluacionDTO;
import cl.instituto.pacifico.ms_titulacion.model.Titulacion;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class TitulacionService {
    private final List<Titulacion> lista = new ArrayList<>();
    private Long contador = 1L;

    // conexión a ms-evaluacion
    private final WebClient client = WebClient.create("http://localhost:8085");

    // crear titulación
    public Titulacion crear(Titulacion titulacion) {
        EvaluacionDTO evaluacion = client.get()
                .uri("/api/evaluaciones/" + titulacion.getEvaluacionId())
                .retrieve()
                .bodyToMono(EvaluacionDTO.class)
                .block();
        if (evaluacion == null) {
            throw new RuntimeException("Evaluación no existe");
        }
        if (!"APROBADO".equalsIgnoreCase(evaluacion.getEstado())) {
            throw new RuntimeException("El estudiante no aprobó");
        }
        titulacion.setId(contador++);
        titulacion.setEstado("TITULADO");
        lista.add(titulacion);
        return titulacion;
    }

    // listar
    public List<Titulacion> listar() {
        return lista;
    }

    // obtener
    public Titulacion obtener(Long id) {
        return lista.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

