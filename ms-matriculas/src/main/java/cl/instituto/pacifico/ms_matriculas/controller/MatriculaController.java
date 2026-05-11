package cl.instituto.pacifico.ms_matriculas.controller;
import cl.instituto.pacifico.ms_matriculas.model.Matricula;
import cl.instituto.pacifico.ms_matriculas.service.MatriculaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {
    private final MatriculaService service;
    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    // Crear matrícula
    @PostMapping
    public Matricula crear(@RequestBody Matricula matricula) {
        return service.crear(matricula);
    }

    // Listar
    @GetMapping
    public List<Matricula> listar() {
        return service.listar();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Matricula obtener(@PathVariable Long id) {
        return service.obtener(id);
    }
}
