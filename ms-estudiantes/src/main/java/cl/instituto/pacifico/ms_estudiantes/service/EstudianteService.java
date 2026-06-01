package cl.instituto.pacifico.ms_estudiantes.service;
import cl.instituto.pacifico.ms_estudiantes.model.Estudiante;
import cl.instituto.pacifico.ms_estudiantes.repository.EstudianteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstudianteService {
    private static final Logger log = LoggerFactory.getLogger(EstudianteService.class);
    private final EstudianteRepository repository;
    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    // LISTAR
    public List<Estudiante> listar() {
        log.info("Listando estudiantes");
        return repository.findAll();
    }

    // BUSCAR POR ID
    public Estudiante buscarPorId(Long id) {
        log.info("Buscando estudiante con ID: {}", id);
        return repository.findById(id).orElse(null);
    }

    // BUSCAR POR RUT
    public Estudiante buscarPorRut(String rut) {
        log.info("Buscando estudiante con RUT: {}", rut);
        return repository.findByRut(rut);
    }

    // CREAR
    public Estudiante guardar(Estudiante estudiante) {
        log.info("Creando estudiante con rut: {}", estudiante.getRut());
        Estudiante nuevo = repository.save(estudiante);
        log.info("Estudiante creado correctamente con ID: {}", nuevo.getId());
        return nuevo;
    }

    // ACTUALIZAR
    public Estudiante actualizar(Long id, Estudiante estudianteActualizado) {
        log.info("Actualizando estudiante con ID: {}", id);
        Estudiante estudiante = repository.findById(id).orElse(null);
        if (estudiante == null) {
            log.error("Estudiante no encontrado");
            return null;
        }
        estudiante.setRut(estudianteActualizado.getRut());
        estudiante.setNombre(estudianteActualizado.getNombre());
        estudiante.setEmail(estudianteActualizado.getEmail());
        estudiante.setTelefono(estudianteActualizado.getTelefono());
        estudiante.setFechaNacimiento(estudianteActualizado.getFechaNacimiento());
        log.info("Estudiante actualizado correctamente");
        return repository.save(estudiante);
    }

    // ELIMINAR
    public Estudiante eliminar(Long id) {
        log.info("Eliminando estudiante con ID: {}", id);
        Estudiante estudiante = repository.findById(id).orElse(null);
        if (estudiante == null) {
            log.error("Estudiante no encontrado");
            return null;
        }
        repository.delete(estudiante);
        log.info("Estudiante eliminado correctamente");
        return estudiante;
    }
}