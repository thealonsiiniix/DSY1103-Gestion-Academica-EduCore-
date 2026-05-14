package cl.instituto.pacifico.ms_academico.service;
import cl.instituto.pacifico.ms_academico.model.Carrera;
import cl.instituto.pacifico.ms_academico.repository.CarreraRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarreraService {
    private final CarreraRepository repository;
    public CarreraService(CarreraRepository repository) {
        this.repository = repository;
    }

    // LISTAR
    public List<Carrera> listar() {
        return repository.findAll();
    }

    // BUSCAR POR ID
    public Carrera buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // BUSCAR POR NOMBRE
    public List<Carrera> buscarPorCarrera(String nombre) {
        return repository.findByCarrera(nombre);
    }

    // CREAR
    public Carrera crear(Carrera carrera) {
        return repository.save(carrera);
    }

    // ACTUALIZAR
    public Carrera actualizar(Long id, Carrera carreraActualizada) {
        Carrera carrera = repository.findById(id).orElse(null);
        if (carrera == null) {
            return null;
        }
        carrera.setCarrera(carreraActualizada.getCarrera());
        carrera.setAsignaturas(carreraActualizada.getAsignaturas());
        carrera.setMallas(carreraActualizada.getMallas());
        carrera.setPrerrequisitos(carreraActualizada.getPrerrequisitos());
        return repository.save(carrera);
    }

    // ELIMINAR
    public boolean eliminar(Long id) {
        Carrera carrera = repository.findById(id).orElse(null);
        if (carrera == null) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}