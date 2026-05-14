package cl.instituto.pacifico.ms_evaluacion.repository;
import cl.instituto.pacifico.ms_evaluacion.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByMatriculaId(Long matriculaId);
}