package cl.instituto.pacifico.ms_academico.controller;

import cl.instituto.pacifico.ms_academico.model.Carrera;
import cl.instituto.pacifico.ms_academico.repository.CarreraRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academico")
public class CarreraController {
    // inyeccion de repo
    public final CarreraRepository academicoRepository;

    // Constructor para el repo
    public CarreraController(CarreraRepository academicoRepository){
        this.academicoRepository = academicoRepository;
    }

    @GetMapping
    public List<Carrera> listarAcdemicos(){
        return academicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Carrera obtenerAcademicoPorId(@PathVariable Long id){
        return academicoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Carrera guardarAcademico(@RequestBody @Validated Carrera academico){
        return academicoRepository.save(academico);
    }

    @DeleteMapping("/{id}")
    public void eliminarAcademico(@PathVariable Long id){
        academicoRepository.deleteById(id);
    }
}
