package cl.instituto.pacifico.ms_estudiantes.controller;
import cl.instituto.pacifico.ms_estudiantes.model.Estudiante;
import cl.instituto.pacifico.ms_estudiantes.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstudianteController {
    private final EstudianteService service;
    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    // LISTAR
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Estudiante> estudiantes = service.listar();
            return ResponseEntity.ok(estudiantes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar estudiantes");
        }
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Estudiante estudiante = service.buscarPorId(id);
            if (estudiante == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Estudiante no encontrado");
            }
            return ResponseEntity.ok(estudiante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar estudiante");
        }
    }

    // BUSCAR POR RUT
    @GetMapping("/rut/{rut}")
    public ResponseEntity<?> buscarPorRut(@PathVariable String rut) {
        try {
            Estudiante estudiante = service.buscarPorRut(rut);
            if (estudiante == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Estudiante no encontrado");
            }
            return ResponseEntity.ok(estudiante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar estudiante");
        }
    }

    // CREAR
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Estudiante estudiante) {
        try {
            if (estudiante.getRut() == null || estudiante.getRut().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El rut es obligatorio");
            }
            if (estudiante.getNombre() == null || estudiante.getNombre().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El nombre es obligatorio");
            }
            if (estudiante.getEmail() == null || estudiante.getEmail().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El email es obligatorio");
            }
            Estudiante nuevoEstudiante = service.guardar(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevoEstudiante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear estudiante");
        }
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Estudiante estudianteActualizado) {
        try {
            if (estudianteActualizado.getRut() == null || estudianteActualizado.getRut().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El rut es obligatorio");
            }
            if (estudianteActualizado.getNombre() == null || estudianteActualizado.getNombre().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El nombre es obligatorio");
            }
            if (estudianteActualizado.getEmail() == null || estudianteActualizado.getEmail().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El email es obligatorio");
            }

            Estudiante estudiante = service.actualizar(id, estudianteActualizado);
            if (estudiante == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Estudiante no encontrado");
            }
            return ResponseEntity.ok(estudiante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar estudiante");
        }
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            Estudiante estudiante = service.eliminar(id);
            if (estudiante == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Estudiante no encontrado");
            }
            return ResponseEntity.ok("Estudiante eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar estudiante");
        }
    }
}