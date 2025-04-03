package com.gym.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReserva")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idClase") // Foreign Key a la tabla clases
    private Clase clase;


@ManyToOne
@JoinColumn(name = "idUsuario") // usa el id en lugar de correo
private User usuario;




    @Column(name = "fechaReserva") // nombre correcto en BD
    private LocalDate fecha;

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
