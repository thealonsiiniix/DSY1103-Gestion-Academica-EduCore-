package cl.instituto.pacifico.ms_docentes.controller;

import cl.instituto.pacifico.ms_docentes.model.Docente;
import cl.instituto.pacifico.ms_docentes.service.DocenteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/docentes")
public class DocenteController {

    private static final Logger log =
            LoggerFactory.getLogger(
                    DocenteController.class);

    private final DocenteService service;

    public DocenteController(
            DocenteService service) {

        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listar() {

        try {

            List<Docente> docentes =
                    service.listar();

            return ResponseEntity.ok(docentes);

        } catch (Exception e) {

            log.error("Error al listar docentes");

            return ResponseEntity
                    .status(
                            HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar docentes");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(
            @PathVariable Long id) {

        try {

            Docente docente =
                    service.buscarPorId(id);

            return ResponseEntity.ok(docente);

        } catch (Exception e) {

            log.error("Error al buscar docente");

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Docente no encontrado");
        }
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<?> buscarPorRut(
            @PathVariable String rut) {

        try {

            Docente docente =
                    service.buscarPorRut(rut);

            return ResponseEntity.ok(docente);

        } catch (Exception e) {

            log.error("Error al buscar rut");

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Docente no encontrado");
        }
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> buscarPorCorreo(
            @PathVariable String correo) {

        try {

            Docente docente =
                    service.buscarPorCorreo(correo);

            return ResponseEntity.ok(docente);

        } catch (Exception e) {

            log.error("Error al buscar correo");

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Docente no encontrado");
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(
            @PathVariable String nombre) {

        try {

            return ResponseEntity.ok(
                    service.buscarPorNombre(nombre));

        } catch (Exception e) {

            log.error("Error al buscar nombre");

            return ResponseEntity
                    .status(
                            HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar docentes");
        }
    }

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<?> buscarPorApellido(
            @PathVariable String apellido) {

        try {

            return ResponseEntity.ok(
                    service.buscarPorApellido(apellido));

        } catch (Exception e) {

            log.error("Error al buscar apellido");

            return ResponseEntity
                    .status(
                            HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar docentes");
        }
    }

    @GetMapping("/{id}/perfil")
    public ResponseEntity<?> obtenerPerfil(
            @PathVariable Long id) {

        try {

            return ResponseEntity.ok(
                    service.obtenerPerfilCompleto(id));

        } catch (Exception e) {

            log.error("Error al obtener perfil");

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Perfil no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(
            @Valid @RequestBody Docente docente) {

        try {

            Docente nuevo =
                    service.guardar(docente);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nuevo);

        } catch (Exception e) {

            log.error("Error al crear docente");

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Docente nuevo) {

        try {

            Docente actualizado =
                    service.actualizar(id, nuevo);

            return ResponseEntity.ok(actualizado);

        } catch (Exception e) {

            log.error("Error al actualizar docente");

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Docente no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @PathVariable Long id) {

        try {

            service.eliminar(id);

            return ResponseEntity
                    .ok("Docente eliminado");

        } catch (Exception e) {

            log.error("Error al eliminar docente");

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Docente no encontrado");
        }
    }
}