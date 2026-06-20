package cl.instituto.pacifico.ms_evaluacion.controller;

import cl.instituto.pacifico.ms_evaluacion.dto.ResultadoDTO;
import cl.instituto.pacifico.ms_evaluacion.model.Evaluacion;
import cl.instituto.pacifico.ms_evaluacion.service.EvaluacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@Tag(
        name = "Evaluaciones",
        description = "Operaciones para gestionar evaluaciones y resultados finales"
)
public class EvaluacionController {

    private static final Logger log =
            LoggerFactory.getLogger(EvaluacionController.class);

    private final EvaluacionService service;

    public EvaluacionController(EvaluacionService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Listar evaluaciones",
            description = "Obtiene todas las evaluaciones registradas"
    )
    public ResponseEntity<?> listar() {

        try {
            log.info("Listando evaluaciones");
            List<Evaluacion> evaluaciones = service.listar();
            return ResponseEntity.ok(evaluaciones);
        } catch (Exception e) {
            log.error("Error al listar evaluaciones");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar evaluaciones");
        }
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar evaluación por ID",
            description = "Busca una evaluación específica utilizando su identificador"
    )
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        try {
            log.info("Buscando evaluación con ID {}", id);
            Evaluacion evaluacion = service.buscarPorId(id);

            if (evaluacion == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Evaluación no encontrada");
            }

            return ResponseEntity.ok(evaluacion);
        } catch (Exception e) {
            log.error("Error al buscar evaluación");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar evaluación");
        }
    }

    @PostMapping
    @Operation(
            summary = "Crear evaluación",
            description = "Registra una nueva evaluación asociada a una matrícula"
    )
    public ResponseEntity<?> guardar(@RequestBody Evaluacion evaluacion) {

        try {
            log.info("Guardando evaluación");
            Evaluacion nueva = service.crear(evaluacion);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nueva);

        } catch (Exception e) {
            log.error("Error al crear evaluación");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear evaluación");
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar evaluación",
            description = "Actualiza los datos de una evaluación existente"
    )
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody Evaluacion evaluacion) {

        try {
            log.info("Actualizando evaluación con ID {}", id);
            Evaluacion actualizada = service.actualizar(id, evaluacion);

            if (actualizada == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Evaluación no encontrada");
            }

            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            log.error("Error al actualizar evaluación");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar evaluación");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar evaluación",
            description = "Elimina una evaluación utilizando su ID"
    )
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        try {
            Evaluacion evaluacion = service.buscarPorId(id);

            if (evaluacion == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Evaluación no encontrada");
            }

            service.eliminar(id);

            return ResponseEntity.ok("Evaluación eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar evaluación");
        }
    }

    @GetMapping("/resultado/{matriculaId}")
    @Operation(
            summary = "Calcular resultado final",
            description = "Calcula el resultado final de una matrícula según sus evaluaciones"
    )
    public ResponseEntity<?> resultadoFinal(@PathVariable Long matriculaId) {

        try {
            log.info("Calculando resultado final");
            ResultadoDTO resultado = service.calcularResultado(matriculaId);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            log.error("Error al calcular resultado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}