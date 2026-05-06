package cl.instituto.pacifico.ms_asistencia.controller;

import cl.instituto.pacifico.ms_asistencia.model.Asistencia;
import cl.instituto.pacifico.ms_asistencia.service.AsistenciaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {
    // inyyectamos a feign para utilizarlo
    private final AsistenciaService service;

    // constructor para consumir feign
    public AsistenciaController(AsistenciaService service){
        this.service = service;
    }

    // crear asistencia
    @PostMapping
    public Asistencia crear(@RequestBody Asistencia asistencia) {
        return service.crear(asistencia);
    }
    // Listar
    @GetMapping
    public List<Asistencia> listar() {
        return service.listar();
    }


}
