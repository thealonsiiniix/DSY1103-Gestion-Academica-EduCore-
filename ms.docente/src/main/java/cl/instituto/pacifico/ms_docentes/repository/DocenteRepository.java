package cl.instituto.pacifico.ms_docentes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.instituto.pacifico.ms_docentes.model.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
}
