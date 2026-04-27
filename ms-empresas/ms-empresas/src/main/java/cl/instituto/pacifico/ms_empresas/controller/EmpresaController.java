package cl.instituto.pacifico.ms_empresas.controller;
import cl.instituto.pacifico.ms_empresas.model.Empresa;
import cl.instituto.pacifico.ms_empresas.repository.EmpresaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {
    private final EmpresaRepository empresaRepository;
    public EmpresaController(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    // Listar
    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Empresa obtenerEmpresaPorId(@PathVariable Long id) {
        return empresaRepository.findById(id).orElse(null);
    }

    // Crear
    @PostMapping
    public Empresa guardarEmpresa(@RequestBody Empresa empresa) {
        return empresaRepository.save(empresa);
    }
}
