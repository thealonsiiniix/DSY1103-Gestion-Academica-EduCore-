package cl.instituto.pacifico.ms_evaluacion.service;

import cl.instituto.pacifico.ms_evaluacion.dto.MatriculaDTO;
import cl.instituto.pacifico.ms_evaluacion.dto.ResultadoDTO;
import cl.instituto.pacifico.ms_evaluacion.exception.BusinessException;
import cl.instituto.pacifico.ms_evaluacion.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_evaluacion.model.Evaluacion;
import cl.instituto.pacifico.ms_evaluacion.repository.EvaluacionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class EvaluacionService {

    private static final Logger log =
            LoggerFactory.getLogger(EvaluacionService.class);

    private final EvaluacionRepository repository;

    public EvaluacionService(EvaluacionRepository repository) {
        this.repository = repository;
    }

    private final WebClient client = WebClient.builder()
            .baseUrl("http://ms-matriculas:8084")
            .defaultHeaders(headers ->
                    headers.setBasicAuth("admin", "1234"))
            .build();

    public Evaluacion crear(Evaluacion evaluacion) {

        log.info("Creando evaluación");

        MatriculaDTO matricula = client.get()
                .uri("/api/v1/matriculas/" + evaluacion.getMatriculaId())
                .retrieve()
                .bodyToMono(MatriculaDTO.class)
                .block();

        if (matricula == null) {
            throw new BusinessException("La matrícula no existe");
        }

        return repository.save(evaluacion);
    }

    public List<Evaluacion> listar() {
        log.info("Listando evaluaciones");
        return repository.findAll();
    }

    public Evaluacion buscarPorId(Long id) {
        log.info("Buscando evaluación con ID {}", id);

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Evaluación no encontrada"));
    }

    public void eliminar(Long id) {
        log.info("Eliminando evaluación con ID {}", id);

        Evaluacion evaluacion = buscarPorId(id);
        repository.delete(evaluacion);
    }

    public Evaluacion actualizar(Long id, Evaluacion nueva) {

        log.info("Actualizando evaluación con ID {}", id);

        Evaluacion evaluacion = buscarPorId(id);

        MatriculaDTO matricula = client.get()
                .uri("/api/v1/matriculas/" + nueva.getMatriculaId())
                .retrieve()
                .bodyToMono(MatriculaDTO.class)
                .block();

        if (matricula == null) {
            throw new BusinessException("La matrícula no existe");
        }

        evaluacion.setMatriculaId(nueva.getMatriculaId());
        evaluacion.setNota(nueva.getNota());

        return repository.save(evaluacion);
    }

    public ResultadoDTO calcularResultado(Long matriculaId) {

        log.info("Calculando resultado para matrícula {}", matriculaId);

        List<Evaluacion> notas =
                repository.findByMatriculaId(matriculaId);

        if (notas.isEmpty()) {
            throw new ResourceNotFoundException("No existen evaluaciones");
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

        return new ResultadoDTO(
                matriculaId,
                promedio,
                estado
        );
    }
}