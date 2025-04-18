package com.gym.domain;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "cita") // asegúrate de que también el nombre de la tabla es correcto
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @Column(name = "email_cliente")
    private String emailCliente;

    private String detalle;

@DateTimeFormat(pattern = "yyyy-MM-dd")
@Temporal(TemporalType.DATE)
private Date fecha;


    // ✅ Relación con el entrenador (User con rol TRAINER)
    @ManyToOne
    @JoinColumn(name = "entrenador_id") // FK en la base de datos
    private User entrenador;



        

public enum EstadoCita {
    PENDIENTE, ACEPTADA, RECHAZADA
}

@Enumerated(EnumType.STRING)
@Column(name = "estado", nullable = false)
private EstadoCita estado = EstadoCita.PENDIENTE;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public User getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(User entrenador) {
        this.entrenador = entrenador;
    }
    
    
    
    public EstadoCita getEstado() {
    return estado;
}

public void setEstado(EstadoCita estado) {
    this.estado = estado;
}

}
