package cl.instituto.pacifico.ms_practicas.controller;

import cl.instituto.pacifico.ms_practicas.model.Practica;
import cl.instituto.pacifico.ms_practicas.service.PracticaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/practica")
public class PracticaController {
    // inyyectamos a feign para utilizarlo
    private final PracticaService service;

    public PracticaController(PracticaService service){
        this.service = service;
    }

    // CREAR PRACTICA - POST
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Practica practica) {
        try {
            if (practica.getRutEstudiante() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("rut estudiante obligatorio");
            }

            if (practica.getIdEmpresa() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id empresa obligatorio");
            }

            Practica nuevaPractia = service.crear(practica);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPractia);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear practica");
        }


    }

    // LISTAR - GET
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Practica> practica = service.listar();
            return ResponseEntity.ok(practica);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }


    //  BUSCAR POR ID -GET
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Practica practica = service.buscarPorId(id);
            if (practica == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("practica no encontrada");
            }

            return ResponseEntity.ok(practica);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    //  BUSCAR POR ESTUDIANTE - GET
    @GetMapping("/estudiante/{rutEstudiante}")
    public ResponseEntity<?> obtenerPorRut(@PathVariable String rutEstudiante){
        try {
            List<Practica> practicas = service.obtenerPorRut(rutEstudiante);

            if (practicas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existen prácticas para este estudiante");
            }

            return ResponseEntity.ok(practicas);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    // ELIMINAR POR ID - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            if (!service.existePorId(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Practica no encontrada");
            }
            service.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body("Practica eliminada");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    // PUT - ACTUALIZAR PRACTICA
    @PutMapping("/{id}")
    // ACTUALIZA LA PRACTICA SI EXISTE Y SI NO MUESTRA MENSAJE DE ERROR
    public ResponseEntity<?> actualizarCompleta(@PathVariable Long id, @RequestBody Practica practica) {
        try {
            return service.actualizarCompleta(id, practica).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar practica");
        }
    }
}
