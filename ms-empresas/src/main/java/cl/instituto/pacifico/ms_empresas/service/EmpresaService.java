package cl.instituto.pacifico.ms_empresas.service;
import cl.instituto.pacifico.ms_empresas.model.Empresa;
import cl.instituto.pacifico.ms_empresas.repository.EmpresaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpresaService {
    private static final Logger log = LoggerFactory.getLogger(EmpresaService.class);
    private final EmpresaRepository repository;
    public EmpresaService(EmpresaRepository repository) {
        this.repository = repository;
    }

    // LISTAR
    public List<Empresa> listar() {
        log.info("Listando empresas");
        return repository.findAll();
    }

    // BUSCAR POR ID
    public Empresa buscarPorId(Long id) {
        log.info("Buscando empresa con ID: {}", id);
        return repository.findById(id).orElse(null);
    }

    // BUSCAR POR RUT
    public Empresa buscarPorRut(String rut) {
        log.info("Buscando empresa con RUT: {}", rut);
        return repository.findByRut(rut).orElse(null);
    }

    // CREAR
    public Empresa crear(Empresa empresa) {
        log.info("Creando empresa: {}", empresa.getNombre());
        Empresa nueva = repository.save(empresa);
        log.info("Empresa creada correctamente con ID: {}", nueva.getId());
        return nueva;
    }

    // ACTUALIZAR
    public Empresa actualizar(Long id, Empresa empresaActualizada) {
        log.info("Actualizando empresa con ID: {}", id);
        Empresa empresa = repository.findById(id).orElse(null);
        if (empresa == null) {
            log.error("Empresa no encontrada");
            return null;
        }
        empresa.setNombre(empresaActualizada.getNombre());
        empresa.setRut(empresaActualizada.getRut());
        empresa.setDireccion(empresaActualizada.getDireccion());
        empresa.setTelefono(empresaActualizada.getTelefono());
        empresa.setEmail(empresaActualizada.getEmail());
        empresa.setConvenioVigente(empresaActualizada.getConvenioVigente());
        log.info("Empresa actualizada correctamente");
        return repository.save(empresa);
    }

    // ELIMINAR
    public boolean eliminar(Long id) {
        log.info("Eliminando empresa con ID: {}", id);
        Empresa empresa = repository.findById(id).orElse(null);
        if (empresa == null) {
            log.error("Empresa no encontrada");
            return false;
        }
        repository.deleteById(id);
        log.info("Empresa eliminada correctamente");
        return true;
    }
}
