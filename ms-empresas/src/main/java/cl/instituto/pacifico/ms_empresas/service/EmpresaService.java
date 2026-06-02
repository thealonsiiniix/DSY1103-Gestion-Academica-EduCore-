package cl.instituto.pacifico.ms_empresas.service;
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
        log.info("Listando empresas");
        return repository.findAll();
    }

    public Empresa buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Empresa no encontrada con ID: " + id));
    }

    public Empresa buscarPorRut(String rut) {
        return repository.findByRut(rut)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Empresa no encontrada con RUT: " + rut));
    }

    public Empresa crear(Empresa empresa) {
        log.info("Creando empresa {}", empresa.getNombre());
        return repository.save(empresa);
    }

    public Empresa actualizar(Long id, Empresa empresaActualizada) {
        Empresa empresa = buscarPorId(id);
        empresa.setNombre(empresaActualizada.getNombre());
        empresa.setRut(empresaActualizada.getRut());
        empresa.setDireccion(empresaActualizada.getDireccion());
        empresa.setTelefono(empresaActualizada.getTelefono());
        empresa.setEmail(empresaActualizada.getEmail());
        empresa.setConvenioVigente(empresaActualizada.getConvenioVigente());
        return repository.save(empresa);
    }

    public void eliminar(Long id) {
        Empresa empresa = buscarPorId(id);
        repository.delete(empresa);
    }
}