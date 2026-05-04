package cl.instituto.pacifico.ms_asistencia.client;


import cl.instituto.pacifico.ms_asistencia.dto.EstudianteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="estudianteClient", url="http://localhost:8081")
public interface EstudianteClient {
    //llamar a al endpoint especifico GET  /api/estudantes/{id} del otro ms
    @GetMapping("/api/v1/estudiantes/{id}")
    EstudianteDTO obtenerEstudiantePorId(@PathVariable Long id);


}
