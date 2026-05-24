package cl.instituto.pacifico.ms_docentes.controller;

import cl.instituto.pacifico.ms_docentes.model.Docente;
import cl.instituto.pacifico.ms_docentes.service.DocenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/docentes")
public class DocenteController {

    private final DocenteService service;

    public DocenteController(DocenteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Docente> docentes = service.listar();
            return ResponseEntity.ok(docentes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar docentes");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Docente docente = service.buscarPorId(id);
            if (docente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Docente no encontrado");
            }
            return ResponseEntity.ok(docente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar docente");
        }
    }
    @GetMapping("/rut/{rut}")
    public ResponseEntity<?> buscarPorRut(@PathVariable String rut) {
        try {
            Docente docente = service.buscarPorRut(rut);
            if (docente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Docente no encontrado");
            }
            return ResponseEntity.ok(docente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar docente");
        }
    }
    @GetMapping("/correo/{correo}")
    public ResponseEntity<?> buscarPorCorreo(@PathVariable String correo) {
        try {
            Docente docente = service.buscarPorCorreo(correo);
            if (docente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Docente no encontrado");
            }
            return ResponseEntity.ok(docente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar docente");
        }
    }
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        try {
            List<Docente> docentes = service.buscarPorNombre(nombre);
            return ResponseEntity.ok(docentes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar docentes");
        }
    }

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<?> buscarPorApellido(@PathVariable String apellido) {
        try {
            List<Docente> docentes = service.buscarPorApellido(apellido);
            return ResponseEntity.ok(docentes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar docentes");
        }
    }

    @GetMapping("/{id}/perfil")
    public ResponseEntity<?> obtenerPerfil(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    service.obtenerPerfilCompleto(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Perfil no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Docente docente) {
        try {
            if (docente.getRut() == null
                    || docente.getRut().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El rut es obligatorio");
            }
            if (docente.getNombre() == null
                    || docente.getNombre().isBlank()) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El nombre es obligatorio");
            }
            if (docente.getCorreo() == null
                    || docente.getCorreo().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El correo es obligatorio");
            }
            Docente nuevoDocente =
                    service.guardar(docente);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevoDocente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear docente");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Docente docenteActualizado) {
        try {
            if (docenteActualizado.getRut() == null
                    || docenteActualizado.getRut().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El rut es obligatorio");
            }
            if (docenteActualizado.getNombre() == null
                    || docenteActualizado.getNombre().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El nombre es obligatorio");
            }
            if (docenteActualizado.getCorreo() == null
                    || docenteActualizado.getCorreo().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El correo es obligatorio");
            }
            Docente docente =
                    service.actualizar(id,docenteActualizado);
            if (docente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Docente no encontrado");
            }
            return ResponseEntity.ok(docente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar docente");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Docente eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar docente");
        }
    }
}