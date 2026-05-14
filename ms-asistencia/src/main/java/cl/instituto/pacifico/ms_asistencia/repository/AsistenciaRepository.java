package cl.instituto.pacifico.ms_asistencia.repository;

import cl.instituto.pacifico.ms_asistencia.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByRutEstudiante(String rutEstudiante);
}