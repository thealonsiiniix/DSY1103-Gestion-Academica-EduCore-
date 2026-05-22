package cl.instituto.pacifico.ms_finanzas.controller;

import cl.instituto.pacifico.ms_finanzas.model.Arancel;
import cl.instituto.pacifico.ms_finanzas.repository.ArancelRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aranceles")
public class ArancelController {

    private final ArancelRepository repository;

    public ArancelController(ArancelRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Arancel> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Arancel obtener(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/rut/{rut}")
    public Arancel getByRut(@PathVariable String rut){
        return repository.findByEstudianteRut(rut);
    }

    @PostMapping
    public Arancel guardar(@RequestBody Arancel arancel) {
        return repository.save(arancel);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}