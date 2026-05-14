package cl.instituto.pacifico.ms_asistencia.service;

import cl.instituto.pacifico.ms_asistencia.dto.EstudianteDTO;
import cl.instituto.pacifico.ms_asistencia.model.Asistencia;
import cl.instituto.pacifico.ms_asistencia.repository.AsistenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {
    // Variable de el repositorio
    public final AsistenciaRepository asistenciaRepository;

    // inyecta el repositorio en el contructor
    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    // DEFINIMOS LA DIRECCION DEL MICROSERVVICIO QUE USAREMOS EN LA VARIABLE CLIENT
    private final WebClient client = WebClient.create("http://localhost:8081");

    // CREAR ASISTENCIA
    public Asistencia crear(Asistencia asistencia){
        // peticion GET al MS de producto
        EstudianteDTO e = client.get()
                .uri("/api/v1/estudiantes/estudiante/"+asistencia.getRutEstudiante()) // endpoint al que llama
                .retrieve() // ejecuta la llamada
                .bodyToMono(EstudianteDTO.class)// cambia de json a obj
                .block(); // detiene el flujo hasta recibir una respuesta
        // Validar que exista
        if (e == null) {
            throw new RuntimeException("Estudiante no existe");
        }

        //  Copiar datos al modelo Asistencia
        asistencia.setRutEstudiante(e.getRut());
        asistencia.setEstudianteId(e.getId());
        asistencia.setNombreEstudiante(e.getNombre());

        // GUARDAR
        return asistenciaRepository.save(asistencia);
    }

    // METODO LISTA TOD0
    public List<Asistencia> listar() {
        return asistenciaRepository.findAll();
    }

    // BUSCAR POR ID
    public Asistencia buscarPorId(Long id) {
        Optional<Asistencia> asistencia = asistenciaRepository.findById(id);
        return asistencia.orElse(null);
    }

    // OBTENER ASISTTENCIA POR RUT ESTUDIANTE
    public List<Asistencia> obtenerPorRut(String rutEstudiante){
        return asistenciaRepository.findByRutEstudiante(rutEstudiante);
    }

    // SI EXISTE X ID
    public boolean existePorId(Long id){
        return asistenciaRepository.existsById(id);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        asistenciaRepository.deleteById(id);
    }

    // ACTUALIZAR LOS DATOS DE LA ASISTENCIA
    public Optional<Asistencia>actualizarCompleta(Long id, Asistencia asistenciaActualizadol){
        return asistenciaRepository.findById(id).map(asistencia -> {

            EstudianteDTO e = client.get()
                    .uri("/api/v1/estudiantes/estudiante/" + asistenciaActualizadol.getRutEstudiante())
                    .retrieve()
                    .bodyToMono(EstudianteDTO.class)
                    .block();

            if (e == null) {
                throw new RuntimeException("El estudiante no existe");
            }

            asistencia.setRutEstudiante(e.getRut());
            asistencia.setEstudianteId(e.getId());
            asistencia.setNombreEstudiante(e.getNombre());

            return asistenciaRepository.save(asistencia);
        });
    }
}
