package cl.instituto.pacifico.ms_evaluacion.dto;

public class ResultadoDTO {
    public Long matriculaId;
    public Double promedio;
    public String estado;

    public ResultadoDTO(Long matriculaId, Double promedio, String estado) {
        this.matriculaId = matriculaId;
        this.promedio = promedio;
        this.estado = estado;
    }
}
