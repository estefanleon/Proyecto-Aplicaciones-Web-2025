package com.gym.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clases")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClase")
    public Long id;

    @Column(name = "nombreClase")
    public String nombre;

    @Column(name = "descripcionClase")
    public String descripcion;

    @Column(name = "horarioClase")
    public String horario;

    @Column(name = "nombreEntrenador")
    public String entrenador;

@Column(name = "imagenClase")
public String imagen;



    @Column(name = "estadoReserva")
    public String estado;
}
