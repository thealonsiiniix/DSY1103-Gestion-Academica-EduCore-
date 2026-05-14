package cl.instituto.pacifico.ms_docentes.repository;

import cl.instituto.pacifico.ms_docentes.model.Docente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocenteRepository
        extends JpaRepository<Docente, Long> {

    Optional<Docente> findByRut(String rut);

    Optional<Docente> findByCorreo(String correo);

    List<Docente> findByNombre(String nombre);

    List<Docente> findByApellido(String apellido);
}
