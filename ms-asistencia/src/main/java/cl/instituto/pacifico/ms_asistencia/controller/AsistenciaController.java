package cl.instituto.pacifico.ms_asistencia.controller;


import cl.instituto.pacifico.ms_asistencia.client.EstudianteClient;
import cl.instituto.pacifico.ms_asistencia.dto.EstudianteDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {
    // inyyectamos a feign para utilizarlo
    private final EstudianteClient estudianteClient;

    // constructor para consumir feign
    public AsistenciaController(EstudianteClient estudianteClient){
        this.estudianteClient = estudianteClient;
    }


    //endpoint para consultar la info del estudiante
    @GetMapping ("/crear/{idEstudiante}")
    public String crearAsistencia(@PathVariable Long idEstudiante){
        // consultar el ms de estudiante
        EstudianteDTO estudiante = estudianteClient.obtenerEstudiantePorId(idEstudiante);

        // si no existe (validaciones en service)
        if(estudiante == null){
            return "no se pudo crear la asistencia por que el estudiante no existe";
        }
        return "asistencia creada exitosamente:"
                +estudiante.getRut()
                +estudiante.getNombre();
    }




}
