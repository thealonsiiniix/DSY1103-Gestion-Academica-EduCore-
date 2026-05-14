package cl.instituto.pacifico.ms_titulacion.controller;
import cl.instituto.pacifico.ms_titulacion.model.Titulacion;
import cl.instituto.pacifico.ms_titulacion.service.TitulacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/titulaciones")
public class TitulacionController {
    private final TitulacionService service;
    public TitulacionController(TitulacionService service) {
        this.service = service;
    }

    // crear
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Titulacion titulacion) {
        try {
            if (titulacion.getMatriculaId() == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("matriculaId obligatorio");
            }
            Titulacion nueva = service.crear(titulacion);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nueva);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // listar
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Titulacion> lista = service.listar();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar");
        }
    }

    // buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        try {
            Titulacion titulacion = service.obtener(id);
            if (titulacion == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Titulacion no encontrada");
            }
            return ResponseEntity.ok(titulacion);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error");
        }
    }

    // eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            Titulacion titulacion = service.obtener(id);
            if (titulacion == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Titulacion no encontrada");
            }
            service.eliminar(id);
            return ResponseEntity.ok("Titulacion eliminada");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error");
        }
    }

    // actualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Titulacion titulacion) {
        try {
            Titulacion actualizada = service.actualizar(id, titulacion);
            if (actualizada == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Titulacion no encontrada");
            }
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error");
        }
    }
}
