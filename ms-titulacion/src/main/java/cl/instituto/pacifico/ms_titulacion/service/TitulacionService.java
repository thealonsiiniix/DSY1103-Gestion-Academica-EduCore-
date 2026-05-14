package cl.instituto.pacifico.ms_titulacion.service;
import cl.instituto.pacifico.ms_titulacion.dto.ResultadoDTO;
import cl.instituto.pacifico.ms_titulacion.model.Titulacion;
import cl.instituto.pacifico.ms_titulacion.repository.TitulacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class TitulacionService {
    private final TitulacionRepository repository;
    public TitulacionService(TitulacionRepository repository) {
        this.repository = repository;
    }

    // conexión ms-evaluacion
    private final WebClient evaluacionClient = WebClient.builder()
            .baseUrl("http://localhost:8085")
            .defaultHeaders(headers ->
                    headers.setBasicAuth("admin", "1234"))
            .build();

    // crear
    public Titulacion crear(Titulacion titulacion) {
        ResultadoDTO resultado = evaluacionClient.get()
                .uri("/api/evaluaciones/resultado/" + titulacion.getMatriculaId())
                .retrieve()
                .bodyToMono(ResultadoDTO.class)
                .block();
        if (resultado == null) {
            throw new RuntimeException("No existe resultado final");
        }
        if (!resultado.getEstado().equalsIgnoreCase("APROBADO")) {
            throw new RuntimeException("El estudiante no cumple requisitos para titularse");
        }
        titulacion.setEstado("TITULADO");
        return repository.save(titulacion);
    }

    // listar
    public List<Titulacion> listar() {
        return repository.findAll();
    }

    // buscar por id
    public Titulacion obtener(Long id) {
        return repository.findById(id).orElse(null);
    }

    // eliminar
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    // actualizar
    public Titulacion actualizar(Long id, Titulacion nueva) {
        Titulacion titulacion = obtener(id);
        if (titulacion == null) {
            return null;
        }
        titulacion.setFecha(nueva.getFecha());
        return repository.save(titulacion);
    }
}

