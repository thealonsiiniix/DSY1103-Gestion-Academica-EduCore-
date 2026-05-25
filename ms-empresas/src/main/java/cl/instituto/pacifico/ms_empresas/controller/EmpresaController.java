package cl.instituto.pacifico.ms_empresas.controller;
import cl.instituto.pacifico.ms_empresas.model.Empresa;
import cl.instituto.pacifico.ms_empresas.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {
    private final EmpresaService service;
    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    // CREAR
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Empresa empresa) {
        try {
            if (empresa.getNombre() == null || empresa.getNombre().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("nombre obligatorio");
            }
            if (empresa.getRut() == null || empresa.getRut().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("rut obligatorio");
            }
            if (empresa.getEmail() == null || empresa.getEmail().isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("email obligatorio");
            }
            if (empresa.getConvenioVigente() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("convenio obligatorio");
            }
            Empresa nuevaEmpresa = service.crear(empresa);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(nuevaEmpresa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear empresa");
        }
    }

    // LISTAR
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Empresa> empresas = service.listar();
            return ResponseEntity.ok(empresas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error al listar empresas");
        }
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Empresa empresa = service.buscarPorId(id);
            if (empresa == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("empresa no encontrada");
            }
            return ResponseEntity.ok(empresa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error al buscar empresa");
        }
    }

    // BUSCAR POR RUT
    @GetMapping("/buscar/rut/{rut}")
    public ResponseEntity<?> buscarPorRut(@PathVariable String rut) {
        try {
            Empresa empresa = service.buscarPorRut(rut);
            if (empresa == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("empresa no encontrada");
            }
            return ResponseEntity.ok(empresa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error");
        }
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Empresa empresa) {
        try {
            Empresa empresaActualizada = service.actualizar(id, empresa);
            if (empresaActualizada == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("empresa no encontrada");
            }
            return ResponseEntity.ok(empresaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error al actualizar empresa");
        }
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            boolean eliminado = service.eliminar(id);
            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("empresa no encontrada");
            }
            return ResponseEntity.ok("empresa eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("error al eliminar empresa");
        }
    }
}
