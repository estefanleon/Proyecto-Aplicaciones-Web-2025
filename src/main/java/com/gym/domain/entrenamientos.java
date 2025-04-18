package com.gym.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "entrenamientos")
public class entrenamientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private boolean completado;

    // Getters y Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public boolean isCompletado() { return completado; }

    public void setCompletado(boolean completado) { this.completado = completado; }
}
