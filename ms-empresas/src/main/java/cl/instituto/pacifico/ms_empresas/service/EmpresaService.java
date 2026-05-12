package cl.instituto.pacifico.ms_empresas.service;
import cl.instituto.pacifico.ms_empresas.model.Empresa;
import cl.instituto.pacifico.ms_empresas.repository.EmpresaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpresaService {
    private final EmpresaRepository repository;
    public EmpresaService(EmpresaRepository repository) {
        this.repository = repository;
    }

    // LISTAR
    public List<Empresa> listar() {
        return repository.findAll();
    }

    // BUSCAR POR ID
    public Empresa buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // BUSCAR POR RUT
    public Empresa buscarPorRut(String rut) {
        return repository.findByRut(rut).orElse(null);
    }

    // CREAR
    public Empresa crear(Empresa empresa) {
        return repository.save(empresa);
    }

    // ACTUALIZAR
    public Empresa actualizar(Long id, Empresa empresaActualizada) {
        Empresa empresa = repository.findById(id).orElse(null);
        if (empresa == null) {
            return null;
        }
        empresa.setNombre(empresaActualizada.getNombre());
        empresa.setRut(empresaActualizada.getRut());
        empresa.setDireccion(empresaActualizada.getDireccion());
        empresa.setTelefono(empresaActualizada.getTelefono());
        empresa.setEmail(empresaActualizada.getEmail());
        empresa.setConvenioVigente(empresaActualizada.getConvenioVigente());
        return repository.save(empresa);
    }

    // ELIMINAR
    public boolean eliminar(Long id) {
        Empresa empresa = repository.findById(id).orElse(null);
        if (empresa == null) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
