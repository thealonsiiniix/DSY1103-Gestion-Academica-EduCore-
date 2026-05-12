package cl.instituto.pacifico.ms_estudiantes.service;
import cl.instituto.pacifico.ms_estudiantes.model.Estudiante;
import cl.instituto.pacifico.ms_estudiantes.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class EstudianteService {
    private final EstudianteRepository repository;
    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    // LISTAR
    public List<Estudiante> listar() {
        return repository.findAll();
    }

    // BUSCAR POR ID
    public Estudiante buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // BUSCAR POR RUT
    public Estudiante buscarPorRut(String rut) {
        return repository.findByRut(rut);
    }

    // CREAR
    public Estudiante guardar(Estudiante estudiante) {
        estudiante.setFechaRegistro(LocalDate.now());
        return repository.save(estudiante);
    }

    // ACTUALIZAR
    public Estudiante actualizar(Long id, Estudiante estudianteActualizado) {
        Estudiante estudiante = repository.findById(id).orElse(null);
        if (estudiante == null) {
            return null;
        }
        estudiante.setRut(estudianteActualizado.getRut());
        estudiante.setNombre(estudianteActualizado.getNombre());
        estudiante.setEmail(estudianteActualizado.getEmail());
        estudiante.setTelefono(estudianteActualizado.getTelefono());
        estudiante.setFechaNacimiento(estudianteActualizado.getFechaNacimiento());
        return repository.save(estudiante);
    }

    // ELIMINAR
    public Estudiante eliminar(Long id) {
        Estudiante estudiante = repository.findById(id).orElse(null);
        if (estudiante != null) {
            repository.delete(estudiante);
        }
        return estudiante;
    }
}