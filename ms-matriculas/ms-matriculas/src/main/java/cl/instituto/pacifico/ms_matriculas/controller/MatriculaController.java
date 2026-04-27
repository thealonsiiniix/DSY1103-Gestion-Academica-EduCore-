package cl.instituto.pacifico.ms_matriculas.controller;
import cl.instituto.pacifico.ms_matriculas.model.Matricula;
import cl.instituto.pacifico.ms_matriculas.repository.MatriculaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matriculas")
public class MatriculaController {
        private final MatriculaRepository matriculaRepository;
        public MatriculaController(MatriculaRepository matriculaRepository) {
            this.matriculaRepository = matriculaRepository;
        }

        // Listar
        @GetMapping
        public List<Matricula> listarMatriculas() {
            return matriculaRepository.findAll();
        }

        // Obtener por ID
        @GetMapping("/{id}")
        public Matricula obtenerMatricula(@PathVariable Long id) {
            return matriculaRepository.findById(id).orElse(null);
        }

        // Crear
        @PostMapping
        public Matricula guardarMatricula(@RequestBody Matricula matricula) {
            return matriculaRepository.save(matricula);
        }
}
