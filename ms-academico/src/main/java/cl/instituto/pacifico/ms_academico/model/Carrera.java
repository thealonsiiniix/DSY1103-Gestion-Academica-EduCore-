package cl.instituto.pacifico.ms_academico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carrera;
    private String asignaturas;
    private String mallas;
    private String prerrequisitos;

    // contructor vacio para que podamos acceder a datos de otros MS
    public Carrera(){
    }

    public Carrera(Long id, String carrera, String asignaturas, String mallas, String prerrequisitos) {
        this.id = id;
        this.carrera = carrera;
        this.asignaturas = asignaturas;
        this.mallas = mallas;
        this.prerrequisitos = prerrequisitos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(String asignaturas) {
        this.asignaturas = asignaturas;
    }

    public String getMallas() {
        return mallas;
    }

    public void setMallas(String mallas) {
        this.mallas = mallas;
    }

    public String getPrerrequisitos() {
        return prerrequisitos;
    }

    public void setPrerrequisitos(String prerrequisitos) {
        this.prerrequisitos = prerrequisitos;
    }
}
