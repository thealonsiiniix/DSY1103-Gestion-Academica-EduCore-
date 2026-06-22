package cl.instituto.pacifico.ms_asistencia.controller;

import cl.instituto.pacifico.ms_asistencia.model.Asistencia;
import cl.instituto.pacifico.ms_asistencia.service.AsistenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/asistencia")
@Tag(
        name = "Asistencia",
        description = "Operaciones relacionadas con la gestión de asistencia de estudiantes"
)
public class AsistenciaController {
    // Variable de el serice
    private final AsistenciaService service;

    // Inyecta el service en el contructor
    public AsistenciaController(AsistenciaService service){
        this.service = service;
    }

    @Operation(
            summary = "Crear asistencia",
            description = "Registra una nueva asistencia validando estudiante"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Asistencia creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    })
    // POST - ENVIA A CREAR
    @PostMapping
    public ResponseEntity<Asistencia> crear(@RequestBody Asistencia asistencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(asistencia));
    }

    @Operation(
            summary = "Listar asistencia",
            description = "Obtiene todas las asistencias registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    })
    // GET - ENVIA A LISTAR
    @GetMapping
    public ResponseEntity<List<Asistencia>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar asistencia por ID",
            description = "Obtiene una asistencia específica según su identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "asistencia encontrada"),
            @ApiResponse(responseCode = "404", description = "asistencia no encontrada")
    })
    // GET - ENVIA A BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Buscar asistencia por estudiante",
            description = "Obtiene una asistencia específica según el rut del estudiante"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "asistencia encontrada"),
            @ApiResponse(responseCode = "404", description = "asistencia no encontrada")
    })
    // GET - ENVIA A OBTENER POR RUT
    @GetMapping("/estudiante/{rutEstudiante}")
    public ResponseEntity<List<Asistencia>> obtenerPorRut(@PathVariable String rutEstudiante){
        return ResponseEntity.ok(service.obtenerPorRut(rutEstudiante));
    }

    @Operation(
            summary = "Eliminar asistencia",
            description = "Elimina una asistencia existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asistencia eliminada"),
            @ApiResponse(responseCode = "404", description = "Asistencia no encontrada")
    })
    // DELETE - ENVIA A ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok("Asistencia eliminada");
    }

    @Operation(
            summary = "Actualizar asistencia",
            description = "Actualiza los datos de una asistencia existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Asistencia actualizada"),
            @ApiResponse(responseCode = "404", description = "Asistencia no encontrada")
    })
    // PUT - ENVIA A ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> actualizarCompleta(@PathVariable Long id, @RequestBody Asistencia asistencia) {
        return ResponseEntity.ok(service.actualizarCompleta(id, asistencia));
    }
}