
package cl.instituto.pacifico.ms_finanzas.controller;

import cl.instituto.pacifico.ms_finanzas.model.Arancel;
import cl.instituto.pacifico.ms_finanzas.service.ArancelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/aranceles")
public class ArancelController {

    private static final Logger log =
            LoggerFactory.getLogger(ArancelController.class);

    private final ArancelService service;

    public ArancelController(ArancelService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            log.info("Listando aranceles");
            List<Arancel> aranceles = service.listar();
            return ResponseEntity.ok(aranceles);
        } catch (Exception e) {
            log.error("Error al listar aranceles");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar aranceles");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        try {
            log.info("Buscando arancel con ID {}", id);
            Arancel arancel = service.buscarPorId(id);
            if (arancel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Arancel no encontrado");
            }
            return ResponseEntity.ok(arancel);
        } catch (Exception e) {
            log.error("Error al buscar arancel");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar arancel");
        }
    }


    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Arancel arancel) {
        try {
            log.info("Guardando arancel");
            Arancel nuevoArancel = service.guardar(arancel);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevoArancel);
        } catch (Exception e) {
            log.error("Error al crear arancel");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear arancel");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            log.info("Eliminando arancel con ID {}", id);
            Arancel arancel = service.eliminar(id);
            if (arancel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Arancel no encontrado");
            }
            return ResponseEntity.ok("Arancel eliminado correctamente");
        } catch (Exception e) {
            log.error("Error al eliminar arancel");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar arancel");
        }
    }
}