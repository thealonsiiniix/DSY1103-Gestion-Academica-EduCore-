package cl.instituto.pacifico.ms_evaluacion.controller;
import cl.instituto.pacifico.ms_evaluacion.dto.ResultadoDTO;
import cl.instituto.pacifico.ms_evaluacion.model.Evaluacion;
import cl.instituto.pacifico.ms_evaluacion.service.EvaluacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {
    private final EvaluacionService service;
    public EvaluacionController(EvaluacionService service) {
        this.service = service;
    }

    // Crear
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Evaluacion evaluacion) {
        try {
            if (evaluacion.getMatriculaId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("matriculaId obligatorio");
            }
            if (evaluacion.getNota() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("nota obligatoria");
            }
            Evaluacion nueva = service.crear(evaluacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // Listar
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Evaluacion> lista = service.listar();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar evaluaciones");
        }
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Evaluacion evaluacion = service.buscarPorId(id);
            if (evaluacion == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Evaluación no encontrada");
            }
            return ResponseEntity.ok(evaluacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error");
        }
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        try {
            Evaluacion actualizada = service.actualizar(id, evaluacion);
            if (actualizada == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Evaluacion no encontrada");
            }
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error");
        }
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            Evaluacion evaluacion = service.buscarPorId(id);
            if (evaluacion == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Evaluación no encontrada");
            }
            service.eliminar(id);
            return ResponseEntity.ok("Evaluación eliminada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error");
        }
    }

    // Resultado final
    @GetMapping("/resultado/{matriculaId}")
    public ResponseEntity<?> resultado(@PathVariable Long matriculaId) {
        try {
            ResultadoDTO resultado = service.calcularResultado(matriculaId);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}