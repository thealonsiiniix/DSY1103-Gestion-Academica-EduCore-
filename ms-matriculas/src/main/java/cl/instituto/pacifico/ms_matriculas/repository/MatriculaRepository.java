package cl.instituto.pacifico.ms_matriculas.repository;
import cl.instituto.pacifico.ms_matriculas.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
