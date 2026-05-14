package cl.instituto.pacifico.ms_practicas.service;

import cl.instituto.pacifico.ms_practicas.controller.PracticaController;
import cl.instituto.pacifico.ms_practicas.dto.ArancelDTO;
import cl.instituto.pacifico.ms_practicas.dto.EmpresaDTO;
import cl.instituto.pacifico.ms_practicas.dto.EstudianteDTO;
import cl.instituto.pacifico.ms_practicas.model.Practica;
import cl.instituto.pacifico.ms_practicas.repository.PracticaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PracticaService {
    // Variable de el repositorio
    public final PracticaRepository practicaRepository;

    // inyecta el repositorio en el contructor
    public PracticaService(PracticaRepository practicaRepository) {
        this.practicaRepository = practicaRepository;
    }

    private final WebClient client = WebClient.create("http://localhost:8081");
    private final WebClient clientEmpresa = WebClient.create("http://localhost:8089");
    private final WebClient clientFinanzas = WebClient.create("http://localhost:8087");

    // CREAR PRACTICA
    public Practica crear(Practica practica){
        // Peticion GET al MS 8081 estudiante
        EstudianteDTO e = client.get()
                .uri("/api/v1/estudiantes/estudiante/"+practica.getRutEstudiante()) // endpoint al que llama
                .retrieve() // ejecuta la llamada
                .bodyToMono(EstudianteDTO.class)// cambia de json a obj
                .block(); // detiene el flujo hasta recibir una respuesta
        // Validar que exista
        if (e == null) {
            throw new RuntimeException("Estudiante no existe");
        }

        // Obtener empresa
        EmpresaDTO emp = clientEmpresa.get()
                .uri("/api/v1/empresas/" + practica.getIdEmpresa())
                .retrieve()
                .bodyToMono(EmpresaDTO.class)
                .block();

        if (emp == null) {
            throw new RuntimeException("Empresa no existe");
        }

        // Obtiene arancel de MS finanzas
        ArancelDTO arancel = clientFinanzas.get()
                .uri("/aranceles/estudiante/" +  practica.getRutEstudiante())
                .headers(h -> h.setBasicAuth("admin", "1234")) //
                .retrieve()
                .bodyToMono(ArancelDTO.class)
                .block();

        if (arancel == null) {
            throw new RuntimeException("No se encontró información financiera");
        }

        if (!arancel.getEstado().equalsIgnoreCase("pagado")) {
            throw new RuntimeException("El estudiante tiene deudas, no puede inscribir su practica");
        }

        // Copia datos al modelo practica
        practica.setRutEstudiante(e.getRut());
        if (practicaRepository.existsByRutEstudiante(practica.getRutEstudiante())) {
            throw new RuntimeException("El estudiante ya tiene una práctica registrada");
        }

        practica.setEstudianteId(e.getId());
        practica.setNombreEstudiante(e.getNombre());
        practica.setEmailEstudiante(e.getEmail());
        practica.setTelefonoEstudiante(e.getTelefono());
        practica.setNombreEmpresa(emp.getNombre());
        practica.setRutEmpresa(emp.getRut());
        practica.setDireccionEmpresa(emp.getDireccion());
        practica.setTelefonoEmpresa(emp.getTelefono());
        practica.setEmailEmpresa(emp.getEmail());
        practica.setConvenioVigenteEmpresa(emp.getConvenioVigente());
        practica.setEstadoArancel(arancel.getEstado());

        // GUARDAR
        return practicaRepository.save(practica);
    }

    // METODO LISTA TOD0
    public List<Practica> listar() {
        return practicaRepository.findAll();
    }

    // BUSCAR POR ID
    public Practica buscarPorId(Long id) {
        Optional<Practica> practica = practicaRepository.findById(id);
        return practica.orElse(null);
    }

    // OBTENER PRACTICA POR RUT ESTUDIANTE
    public List<Practica> obtenerPorRut(String rutEstudiante){
        return practicaRepository.findByRutEstudiante(rutEstudiante);
    }

    // SI EXISTE X ID
    public boolean existePorId(Long id){
        return practicaRepository.existsById(id);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        practicaRepository.deleteById(id);
    }

    // ACTUALIZAR LOS DATOS DE LA PRACTICA
    public Optional<Practica>actualizarCompleta(Long id, Practica practicaActualizadol){
        return practicaRepository.findById(id).map(practica -> {
            // VALIDAR DUPLICADO
            if (practicaRepository.existsByRutEstudianteAndIdNot(
                    practicaActualizadol.getRutEstudiante(), id)) {
                throw new RuntimeException("Ya existe una práctica con este rut");
            }
            practica.setRutEstudiante(practicaActualizadol.getRutEstudiante());
            practica.setIdEmpresa(practicaActualizadol.getIdEmpresa());
            return practicaRepository.save(practica);
        });
    }
}
