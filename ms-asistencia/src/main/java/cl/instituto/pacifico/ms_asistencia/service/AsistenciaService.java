package cl.instituto.pacifico.ms_asistencia.service;

import cl.instituto.pacifico.ms_asistencia.dto.EstudianteDTO;
import cl.instituto.pacifico.ms_asistencia.model.Asistencia;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsistenciaService {
    private final List<Asistencia> lista = new ArrayList<>();
    private Long contador = 1L;


    // definimos la direccion del otro servicios que vamos a usar para la comucarnos
    // se guarda el ms en la variable cliente para asi llamarlo facilmente
    private final WebClient client = WebClient.create("http://localhost:8081");

    // Crear una asistencia
    public Asistencia crear(Asistencia asistencia){
        // peticion GET al MS de producto
        EstudianteDTO e = client.get()
                .uri("/api/v1/estudiantes/"+asistencia.getEstudianteId()) // endpoint al que llama
                .retrieve() // ejecuta la llamada
                .bodyToMono(EstudianteDTO.class)// cambia de json a obj
                .block(); // detiene el flujo hasta recibir una respuesta
        // 🔹 2. Validar que exista
        if (e == null) {
            throw new RuntimeException("Estudiante no existe");
        }

        // 🔹 3. Copiar datos al modelo Asistencia
        asistencia.setRut(e.getRut());
        asistencia.setEstudiante(e.getNombre());

        // 🔹 4. Guardar (por ahora en memoria)
        asistencia.setId(contador++);
        lista.add(asistencia);
        return asistencia;
    }

    // metodo listar tod0
    public List<Asistencia> listar() {
        return lista;
    }
}
