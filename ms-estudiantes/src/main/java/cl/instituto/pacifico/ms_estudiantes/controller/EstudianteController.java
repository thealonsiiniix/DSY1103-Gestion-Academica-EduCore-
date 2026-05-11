package cl.instituto.pacifico.ms_estudiantes.controller;
import cl.instituto.pacifico.ms_estudiantes.model.Estudiante;
import cl.instituto.pacifico.ms_estudiantes.repository.EstudianteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstudianteController {
        private final EstudianteRepository estudianteRepository;
        public EstudianteController(EstudianteRepository estudianteRepository) {
            this.estudianteRepository = estudianteRepository;
        }

        // Listar todos
        @GetMapping
        public List<Estudiante> listarEstudiantes() {
            return estudianteRepository.findAll();
        }

        // Obtener por ID
        @GetMapping("/{id}")
        public Estudiante obtenerEstudiantePorId(@PathVariable Long id) {
            return estudianteRepository.findById(id).orElse(null);
        }

        @GetMapping("/estudiante/{rut}")
        public Estudiante  getByRut(@PathVariable String rut){
            return estudianteRepository.findByRut(rut);
        }

        // Crear
        @PostMapping
        public Estudiante guardarEstudiante(@RequestBody Estudiante estudiante) {
            return estudianteRepository.save(estudiante);
        }
    }