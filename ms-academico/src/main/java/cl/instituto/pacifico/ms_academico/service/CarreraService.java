package cl.instituto.pacifico.ms_academico.service;
import cl.instituto.pacifico.ms_academico.exception.ResourceNotFoundException;
import cl.instituto.pacifico.ms_academico.model.Carrera;
import cl.instituto.pacifico.ms_academico.repository.CarreraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarreraService {
    private static final Logger log = LoggerFactory.getLogger(CarreraService.class);
    private final CarreraRepository repository;
    public CarreraService(CarreraRepository repository) {
        this.repository = repository;
    }

    // LISTAR
    public List<Carrera> listar() {
        log.info("Listando academico");
        return repository.findAll();
    }

    // BUSCAR POR ID
    public Carrera buscarPorId(Long id) {
        log.info("Buscando Carrera con ID: {}", id);
        return repository.findById(id).orElseThrow(() -> {
                    log.warn("Carrera no encontrada con ID: {}", id);
                    return new ResourceNotFoundException("Carrera no encontrada con ID: " + id);
                });
    }

    // BUSCAR POR NOMBRE
    public List<Carrera> buscarPorCarrera(String nombre) {
        log.info("Buscando carrera con nombre: {}", nombre);
        return repository.findByCarrera(nombre);
    }

    // CREAR
    public Carrera crear(Carrera carrera) {
        log.info("Creando carrera con nombre: {}", carrera.getCarrera());
        return repository.save(carrera);
    }

    // ACTUALIZAR
    public Carrera actualizar(Long id, Carrera carreraActualizada) {
        log.info("Actualizando carrera con ID: {}", id);
        Carrera carrera = repository.findById(id).orElse(null);
        if (carrera == null) {
            log.error("Carrera no encontrada");
            return null;
        }
        carrera.setCarrera(carreraActualizada.getCarrera());
        carrera.setAsignaturas(carreraActualizada.getAsignaturas());
        carrera.setMallas(carreraActualizada.getMallas());
        carrera.setPrerrequisitos(carreraActualizada.getPrerrequisitos());
        log.info("Carrera actualizada correctamente");
        return repository.save(carrera);
    }

    // ELIMINAR
    public boolean eliminar(Long id) {
        log.info("Eliminando carrera con ID: {}", id);
        Carrera carrera = repository.findById(id).orElse(null);
        if (carrera == null) {
            log.error("Carrera no encontrado");
            return false;
        }
        repository.deleteById(id);
        log.info("Carrera eliminada correctamente");
        return true;
    }
}