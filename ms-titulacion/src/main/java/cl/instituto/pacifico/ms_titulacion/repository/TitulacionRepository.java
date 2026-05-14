package cl.instituto.pacifico.ms_titulacion.repository;
import cl.instituto.pacifico.ms_titulacion.model.Titulacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitulacionRepository extends JpaRepository<Titulacion, Long> {
}
