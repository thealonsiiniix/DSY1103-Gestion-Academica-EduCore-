package cl.instituto.pacifico.ms_practicas.repository;

import cl.instituto.pacifico.ms_practicas.model.Practica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticaRepository extends JpaRepository<Practica, Long> {

    // Para buscar datos
    List<Practica> findByRutEstudiante(String rutEstudiante);

    // Para validar existencia
    boolean existsByRutEstudiante(String rutEstudiante);

    // Si Existe un rut igual pero con otro ID
    boolean existsByRutEstudianteAndIdNot(String rutEstudiante, Long id);
}
