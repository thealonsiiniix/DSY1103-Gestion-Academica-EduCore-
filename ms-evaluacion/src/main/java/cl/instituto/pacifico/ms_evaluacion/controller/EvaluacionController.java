package cl.instituto.pacifico.ms_evaluacion.controller;
import cl.instituto.pacifico.ms_evaluacion.dto.ResultadoDTO;
import cl.instituto.pacifico.ms_evaluacion.model.Evaluacion;
import cl.instituto.pacifico.ms_evaluacion.service.EvaluacionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {
    private final EvaluacionService service;
    public EvaluacionController(EvaluacionService service) {
        this.service = service;
    }

    // Crear evaluación
    @PostMapping
    public Evaluacion crear(@RequestBody Evaluacion evaluacion) {
        return service.crear(evaluacion);
    }

    // Listar
    @GetMapping
    public List<Evaluacion> listar() {
        return service.listar();
    }

    // Obtener resultado final
    @GetMapping("/resultado/{matriculaId}")
    public ResultadoDTO resultado(@PathVariable Long matriculaId) {
        return service.calcularResultado(matriculaId);
    }
}
