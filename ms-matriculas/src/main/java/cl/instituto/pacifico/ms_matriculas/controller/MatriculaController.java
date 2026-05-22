package cl.instituto.pacifico.ms_matriculas.controller;
import cl.instituto.pacifico.ms_matriculas.model.Matricula;
import cl.instituto.pacifico.ms_matriculas.service.MatriculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/matriculas")
public class MatriculaController {
    private final MatriculaService service;
    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    // CREAR
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Matricula matricula) {
        try {
            if (matricula.getEstudianteId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El ID del estudiante es obligatorio");
            }
            if (matricula.getCarreraId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El ID de carrera es obligatorio");
            }
            if (matricula.getSeccion() == null || matricula.getSeccion().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("La sección es obligatoria");
            }
            Matricula nueva = service.crear(matricula);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nueva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // LISTAR
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Matricula> lista = service.listar();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar matrículas");
        }
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        try {
            Matricula matricula = service.obtener(id);
            if (matricula == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Matrícula no encontrada");
            }
            return ResponseEntity.ok(matricula);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar matrícula");
        }
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Matricula matricula) {
        try {
            if (matricula.getCarreraId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El ID de carrera es obligatorio");
            }
            if (matricula.getSeccion() == null || matricula.getSeccion().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("La sección es obligatoria");
            }
            Matricula actualizada = service.actualizar(id, matricula);
            if (actualizada == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Matrícula no encontrada");
            }
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            Matricula matricula = service.obtener(id);
            if (matricula == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Matrícula no encontrada");
            }
            service.eliminar(id);
            return ResponseEntity.ok("Matrícula eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar matrícula");
        }
    }
}
