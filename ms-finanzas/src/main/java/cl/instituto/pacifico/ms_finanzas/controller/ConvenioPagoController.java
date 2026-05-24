package cl.instituto.pacifico.ms_finanzas.controller;

import cl.instituto.pacifico.ms_finanzas.model.ConvenioPago;
import cl.instituto.pacifico.ms_finanzas.service.ConvenioPagoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/convenios")
public class ConvenioPagoController {

    private static final Logger log = LoggerFactory.getLogger(ConvenioPagoController.class);

    private final ConvenioPagoService service;

    public ConvenioPagoController(ConvenioPagoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listar() {

        try {
            log.info("Listando convenios de pago");
            List<ConvenioPago> convenios = service.listar();
            return ResponseEntity.ok(convenios);
        } catch (Exception e) {
            log.error("Error al listar convenios");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar convenios");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        try {
            log.info("Buscando convenio con ID {}", id);
            ConvenioPago convenio = service.buscarPorId(id);
            if (convenio == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Convenio no encontrado");
            }
            return ResponseEntity.ok(convenio);
        } catch (Exception e) {
            log.error("Error al buscar convenio");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar convenio");
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody ConvenioPago convenioPago) {

        try {
            log.info("Guardando convenio");
            ConvenioPago nuevoConvenio = service.guardar(convenioPago);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevoConvenio);
        } catch (Exception e) {
            log.error("Error al crear convenio");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear convenio");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ConvenioPago convenioPago) {
        try {
            log.info("Actualizando convenio con ID {}", id);
            ConvenioPago convenioActualizado = service.actualizar(id, convenioPago);
            if (convenioActualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Convenio no encontrado");
            }

            return ResponseEntity.ok(convenioActualizado);
        } catch (Exception e) {
            log.error("Error al actualizar convenio");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar convenio");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            log.info("Eliminando convenio con ID {}", id);
            ConvenioPago convenio = service.eliminar(id);
            if (convenio == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Convenio no encontrado");
            }
            return ResponseEntity.ok("Convenio eliminado correctamente");
        } catch (Exception e) {
            log.error("Error al eliminar convenio");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar convenio");
        }
    }
}
