package cl.instituto.pacifico.ms_estudiantes.repository;
import cl.instituto.pacifico.ms_estudiantes.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Estudiante findByRut(String rut);
    Optional<Estudiante> findByEmail(String email);
}
