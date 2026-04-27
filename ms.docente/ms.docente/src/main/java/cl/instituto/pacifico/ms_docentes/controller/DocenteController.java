package cl.instituto.pacifico.ms_docentes.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import cl.instituto.pacifico.ms_docentes.model.Docente;
import cl.instituto.pacifico.ms_docentes.repository.DocenteRepository;

@RestController
@RequestMapping("/api/v1/docentes")
public class DocenteController {

    private final DocenteRepository repository;

    public DocenteController(DocenteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Docente> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Docente obtener(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
    }

    @PostMapping
    public Docente crear(@RequestBody Docente docente) {
        return repository.save(docente);
    }

    @PutMapping("/{id}")
    public Docente actualizar(@PathVariable Long id, @RequestBody Docente nuevo) {
        Docente docente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));

        docente.setNombre(nuevo.getNombre());
        docente.setApellido(nuevo.getApellido());
        docente.setRut(nuevo.getRut());
        docente.setCorreo(nuevo.getCorreo());

        return repository.save(docente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
