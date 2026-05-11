package cl.instituto.pacifico.ms_titulacion.controller;
import cl.instituto.pacifico.ms_titulacion.model.Titulacion;
import cl.instituto.pacifico.ms_titulacion.service.TitulacionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/titulaciones")
public class TitulacionController {
    private final TitulacionService service;
    public TitulacionController(TitulacionService service) {
        this.service = service;
    }

    @PostMapping
    public Titulacion crear(@RequestBody Titulacion titulacion) {
        return service.crear(titulacion);
    }

    @GetMapping
    public List<Titulacion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Titulacion obtener(@PathVariable Long id) {
        return service.obtener(id);
    }
}
