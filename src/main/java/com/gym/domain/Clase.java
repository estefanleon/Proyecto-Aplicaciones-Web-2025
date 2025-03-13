package com.gym.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clases")
public class Clase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClase;
    
    @Column(nullable = false, length = 100)
    private String nombreClase;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcionClase;
    
    @Column(nullable = false, length = 50)
    private String horarioClase;
    
    @Column(nullable = false, length = 100)
    private String nombreEntrenador;
    
    @Column(length = 255)
    private String imagenClase;
    
    @Column(nullable = false)
    private boolean reservada = false; // Por defecto, la clase no está reservada
}
