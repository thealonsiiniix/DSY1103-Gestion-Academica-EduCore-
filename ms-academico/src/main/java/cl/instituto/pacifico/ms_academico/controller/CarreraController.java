package cl.instituto.pacifico.ms_academico.controller;
import cl.instituto.pacifico.ms_academico.model.Carrera;
import cl.instituto.pacifico.ms_academico.service.CarreraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/academico")
public class CarreraController {
    private final CarreraService service;
    public CarreraController(CarreraService service) {
        this.service = service;
    }

    // CREAR
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Carrera carrera) {
        try {
            if (carrera.getCarrera() == null || carrera.getCarrera().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("nombre carrera obligatorio");
            }
            if (carrera.getAsignaturas() == null || carrera.getAsignaturas().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("asignaturas obligatorias");
            }
            Carrera nuevaCarrera = service.crear(carrera);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevaCarrera);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // LISTAR
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Carrera> carreras = service.listar();
            return ResponseEntity.ok(carreras);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error al listar carreras");
        }
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Carrera carrera = service.buscarPorId(id);
            if (carrera == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("carrera no encontrada");
            }
            return ResponseEntity.ok(carrera);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error al buscar carrera");
        }
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Carrera carrera) {
        try {
            Carrera carreraActualizada = service.actualizar(id, carrera);
            if (carreraActualizada == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("carrera no encontrada");
            }
            return ResponseEntity.ok(carreraActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error al actualizar carrera");
        }
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            boolean eliminado = service.eliminar(id);
            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("carrera no encontrada");
            }
            return ResponseEntity.ok("carrera eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error al eliminar carrera");
        }
    }

    // BUSCAR POR NOMBRE
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> buscarPorCarrera(@PathVariable String nombre) {
        try {
            return ResponseEntity.ok(service.buscarPorCarrera(nombre));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error");
        }
    }
}
