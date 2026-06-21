package cl.instituto.pacifico.ms_titulacion.service;
import cl.instituto.pacifico.ms_titulacion.dto.ResultadoDTO;
import cl.instituto.pacifico.ms_titulacion.exception.BusinessException;
import cl.instituto.pacifico.ms_titulacion.model.Titulacion;
import cl.instituto.pacifico.ms_titulacion.repository.TitulacionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TitulacionService {
    private static final Logger log = LoggerFactory.getLogger(TitulacionService.class);
    private final TitulacionRepository repository;
    public TitulacionService(TitulacionRepository repository) {
        this.repository = repository;
    }

    private final WebClient evaluacionClient = WebClient.builder()
            .baseUrl("http://ms-evaluacion:8085")
            .defaultHeaders(headers -> headers.setBasicAuth("admin", "1234"))
            .build();

    public Titulacion crear(Titulacion titulacion) {
        log.info("Consultando ms-evaluacion para matrícula ID: {}",
                titulacion.getMatriculaId());
        ResultadoDTO resultado = evaluacionClient.get()
                .uri("/api/v1/evaluaciones/resultado/" + titulacion.getMatriculaId())
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), response ->
                        response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(
                                        new BusinessException("No existe evaluación para la matrícula")
                                ))
                )
                .onStatus(status -> status.is5xxServerError(), response ->
                        response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(
                                        new BusinessException("Servicio de evaluación no disponible")
                                ))
                )
                .bodyToMono(ResultadoDTO.class)
                .block();
        if (resultado == null) {
            throw new BusinessException("No existe resultado de evaluación");
        }
        if (!"APROBADO".equalsIgnoreCase(resultado.getEstado())) {
            throw new BusinessException("El estudiante no cumple requisitos para titularse");
        }
        if (repository.existsByMatriculaId(titulacion.getMatriculaId())) {
            throw new BusinessException("La matrícula ya tiene una titulación");
        }
        titulacion.setEstado("TITULADO");
        return repository.save(titulacion);
    }

    // listar
    public List<Titulacion> listar() {
        log.info("Listando titulaciones");
        return repository.findAll();
    }

    // buscar por id
    public Titulacion obtener(Long id) {
        log.info("Buscando titulación con ID: {}", id);
        return repository.findById(id).orElse(null);
    }

    // eliminar
    public void eliminar(Long id) {
        log.info("Eliminando titulación con ID: {}", id);
        repository.deleteById(id);
        log.info("Titulación eliminada correctamente");
    }

    // actualizar
    public Titulacion actualizar(Long id, Titulacion nueva) {
        log.info("Actualizando titulación con ID: {}", id);
        Titulacion titulacion = obtener(id);
        if (titulacion == null) {
            log.error("Titulación no encontrada");
            return null;
        }
        titulacion.setFecha(nueva.getFecha());
        titulacion.setEstado(nueva.getEstado());
        log.info("Titulación actualizada correctamente");
        return repository.save(titulacion);
    }
}