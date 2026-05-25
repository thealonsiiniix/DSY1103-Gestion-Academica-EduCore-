package cl.instituto.pacifico.ms_asistencia.controller;

import cl.instituto.pacifico.ms_asistencia.model.Asistencia;
import cl.instituto.pacifico.ms_asistencia.service.AsistenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/asistencia")
public class AsistenciaController {
    // Variable de el serice
    private final AsistenciaService service;

    // Inyecta el service en el contructor
    public AsistenciaController(AsistenciaService service){
        this.service = service;
    }

    // POST - ENVIA A CREAR
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Asistencia asistencia) {
        try {
            if (asistencia.getRutEstudiante() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("rut estudiante obligatorio");
            }
            Asistencia nuevaAsistencia = service.crear(asistencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAsistencia);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear asistencias");
        }
    }

    // GET - ENVIA A LISTAR
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Asistencia> asistencia = service.listar();
            return ResponseEntity.ok(asistencia);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    // GET - ENVIA A BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Asistencia asistencia = service.buscarPorId(id);
            if (asistencia == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asistencia no encontrada");
            }

            return ResponseEntity.ok(asistencia);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    // GET - ENVIA A OBTENER POR RUT
    @GetMapping("/estudiante/{rutEstudiante}")
    public List<Asistencia> obtenerPorRut(@PathVariable String rutEstudiante){
        return service.obtenerPorRut(rutEstudiante);
    }

    // DELETE - ENVIA A ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
             if (!service.existePorId(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asistencia no encontrada");
            }
            service.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body("Asistencia eliminada");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    // PUT - ENVIA A ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCompleta(@PathVariable Long id, @RequestBody Asistencia asistencia) {
        try {
            if (!service.existePorId(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asistencia no encontrada con id: " + id);
            }

            Asistencia actualizada = service.actualizarCompleta(id, asistencia).get();
            return ResponseEntity.ok(actualizada);

        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar asistencia");
        }
    }
}