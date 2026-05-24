package cl.instituto.pacifico.ms_finanzas.controller;

import cl.instituto.pacifico.ms_finanzas.model.Beca;
import cl.instituto.pacifico.ms_finanzas.service.BecaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/becas")
public class BecaController {

    private static final Logger log =
            LoggerFactory.getLogger(BecaController.class);

    private final BecaService service;

    public BecaController(BecaService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            log.info("Listando becas");
            List<Beca> becas = service.listar();
            return ResponseEntity.ok(becas);
        } catch (Exception e) {
            log.error("Error al listar becas");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar becas");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            log.info("Buscando beca con ID {}", id);
            Beca beca = service.buscarPorId(id);
            if (beca == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Beca no encontrada");
            }
            return ResponseEntity.ok(beca);
        } catch (Exception e) {
            log.error("Error al buscar beca");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar beca");
        }
    }


    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Beca beca) {
        try {
            log.info("Guardando beca");
            Beca nuevaBeca = service.guardar(beca);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevaBeca);
        } catch (Exception e) {
            log.error("Error al crear beca");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear beca");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            log.info("Eliminando beca con ID {}", id);
            Beca beca = service.eliminar(id);
            if (beca == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Beca no encontrada");
            }
            return ResponseEntity.ok("Beca eliminada correctamente");
        } catch (Exception e) {
            log.error("Error al eliminar beca");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar beca");
        }
    }
}