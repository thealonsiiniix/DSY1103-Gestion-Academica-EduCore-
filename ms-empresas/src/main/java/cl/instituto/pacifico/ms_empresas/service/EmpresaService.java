package cl.instituto.pacifico.ms_empresas.service;
import cl.instituto.pacifico.ms_empresas.exception.BusinessException;
import cl.instituto.pacifico.ms_empresas.exception.ResourceNotFoundException;
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

    public List<Empresa> listar() {
        log.info("Listando todas las empresas");
        return repository.findAll();
    }

    public Empresa buscarPorId(Long id) {
        log.info("Buscando empresa con ID: {}", id);
        Empresa empresa = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Empresa no encontrada con ID: {}", id);
                    return new ResourceNotFoundException(
                            "Empresa no encontrada con ID: " + id);
                });
        log.info("Empresa encontrada: {}", empresa.getNombre());
        return empresa;
    }

    public Empresa buscarPorRut(String rut) {
        log.info("Buscando empresa con RUT: {}", rut);
        Empresa empresa = repository.findByRut(rut)
                .orElseThrow(() -> {
                    log.warn("Empresa no encontrada con RUT: {}", rut);
                    return new ResourceNotFoundException(
                            "Empresa no encontrada con RUT: " + rut);
                });
        log.info("Empresa encontrada: {}", empresa.getNombre());
        return empresa;
    }

    public Empresa crear(Empresa empresa) {
        log.info("Intentando crear empresa con RUT: {}", empresa.getRut());
        if (repository.findByRut(empresa.getRut()).isPresent()) {
            log.warn("Intento de crear empresa con RUT duplicado: {}",
                    empresa.getRut());
            throw new BusinessException(
                    "Ya existe una empresa con el rut: " + empresa.getRut());
        }
        if (repository.findByEmail(empresa.getEmail()).isPresent()) {
            log.warn("Intento de crear empresa con email duplicado: {}",
                    empresa.getEmail());
            throw new BusinessException(
                    "Ya existe una empresa con el email: " + empresa.getEmail());
        }
        Empresa nueva = repository.save(empresa);
        log.info("Empresa creada correctamente con ID: {}", nueva.getId());
        return nueva;
    }

    public Empresa actualizar(Long id, Empresa empresaActualizada) {
        log.info("Actualizando empresa con ID: {}", id);
        Empresa empresa = buscarPorId(id);
        repository.findByRut(empresaActualizada.getRut())
                .filter(e -> !e.getId().equals(id))
                .ifPresent(e -> {
                    throw new BusinessException(
                            "Ya existe una empresa con el rut: "
                                    + empresaActualizada.getRut());
                });
        repository.findByEmail(empresaActualizada.getEmail())
                .filter(e -> !e.getId().equals(id))
                .ifPresent(e -> {
                    throw new BusinessException(
                            "Ya existe una empresa con el email: "
                                    + empresaActualizada.getEmail());
                });
        empresa.setNombre(empresaActualizada.getNombre());
        empresa.setRut(empresaActualizada.getRut());
        empresa.setDireccion(empresaActualizada.getDireccion());
        empresa.setTelefono(empresaActualizada.getTelefono());
        empresa.setEmail(empresaActualizada.getEmail());
        empresa.setConvenioVigente(empresaActualizada.getConvenioVigente());
        return repository.save(empresa);
    }

    public void eliminar(Long id) {
        log.info("Eliminando empresa con ID: {}", id);
        Empresa empresa = buscarPorId(id);
        repository.delete(empresa);
        log.info("Empresa eliminada correctamente con ID: {}", id);
    }
}