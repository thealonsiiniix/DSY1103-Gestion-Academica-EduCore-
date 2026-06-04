package cl.instituto.pacifico.ms_docentes.controller;

import cl.instituto.pacifico.ms_docentes.model.Docente;
import cl.instituto.pacifico.ms_docentes.service.DocenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/docentes")

@Tag(
        name = "Docentes",
        description = "Operaciones relacionadas con la gestión de docentes"
)
public class DocenteController {

    private final DocenteService service;

    public DocenteController(DocenteService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar docentes",
            description = "Obtiene todos los docentes registrados"
    )
    @GetMapping
    public ResponseEntity<List<Docente>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar docente por ID",
            description = "Obtiene un docente según su identificador"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Docente> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarPorId(id));
    }

    @Operation(
            summary = "Buscar docente por RUT",
            description = "Obtiene un docente según su RUT"
    )
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Docente> buscarPorRut(
            @PathVariable String rut) {

        return ResponseEntity.ok(
                service.buscarPorRut(rut));
    }

    @Operation(
            summary = "Buscar docente por correo",
            description = "Obtiene un docente según su correo"
    )
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Docente> buscarPorCorreo(
            @PathVariable String correo) {

        return ResponseEntity.ok(
                service.buscarPorCorreo(correo));
    }

    @Operation(
            summary = "Buscar docentes por nombre",
            description = "Obtiene docentes según su nombre"
    )
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Docente>> buscarPorNombre(
            @PathVariable String nombre) {

        return ResponseEntity.ok(
                service.buscarPorNombre(nombre));
    }

    @Operation(
            summary = "Buscar docentes por apellido",
            description = "Obtiene docentes según su apellido"
    )
    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<Docente>> buscarPorApellido(
            @PathVariable String apellido) {

        return ResponseEntity.ok(
                service.buscarPorApellido(apellido));
    }

    @Operation(
            summary = "Obtener perfil completo",
            description = "Obtiene el perfil completo del docente"
    )
    @GetMapping("/{id}/perfil")
    public ResponseEntity<?> obtenerPerfil(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.obtenerPerfilCompleto(id));
    }

    @Operation(
            summary = "Crear docente",
            description = "Registra un nuevo docente"
    )
    @PostMapping
    public ResponseEntity<Docente> guardar(
            @Valid @RequestBody Docente docente) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.guardar(docente));
    }

    @Operation(
            summary = "Actualizar docente",
            description = "Actualiza un docente existente"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Docente> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Docente docente) {

        return ResponseEntity.ok(
                service.actualizar(id, docente));
    }

    @Operation(
            summary = "Eliminar docente",
            description = "Elimina un docente por ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok(
                "Docente eliminado correctamente");
    }
}