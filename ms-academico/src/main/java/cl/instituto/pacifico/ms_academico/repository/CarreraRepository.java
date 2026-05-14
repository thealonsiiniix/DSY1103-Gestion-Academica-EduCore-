package cl.instituto.pacifico.ms_academico.repository;
import cl.instituto.pacifico.ms_academico.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    List<Carrera> findByCarrera(String carrera);
}