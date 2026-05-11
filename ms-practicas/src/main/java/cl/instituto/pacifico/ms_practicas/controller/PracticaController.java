package cl.instituto.pacifico.ms_practicas.controller;

import cl.instituto.pacifico.ms_practicas.model.Practica;
import cl.instituto.pacifico.ms_practicas.service.PracticaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/practica")
public class PracticaController {
    // inyyectamos a feign para utilizarlo
    private final PracticaService service;

    public PracticaController(PracticaService service){
        this.service = service;
    }

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

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Listar
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Practica> practica = service.listar();
            return ResponseEntity.ok(practica);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }


    // 🔍 BUSCAR POR ID
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
}
