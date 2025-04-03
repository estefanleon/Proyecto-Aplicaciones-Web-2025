package com.gym.service;

import com.gym.domain.Reserva;
import com.gym.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public void guardar(Reserva reserva) {
        reservaRepository.save(reserva);
    }

    // ✅ Verificar si ya existe una reserva de una clase para un usuario por correo
    public boolean estaReservadaPorUsuario(Long claseId, String correoUsuario) {
        return reservaRepository.existeReservaPorClaseYUsuario(claseId, correoUsuario);
    }

    
    
    
    
public void cancelarReservaPorClaseYCorreo(Long idClase, String correo) {
    Reserva reserva = reservaRepository.findByClaseIdAndUsuarioEmail(idClase, correo);
    if (reserva != null) {
        reservaRepository.delete(reserva);
    }
}

    // ✅ Método para encontrar una reserva por clase y correo
    public Reserva findByClaseIdAndUsuarioEmail(Long idClase, String email) {
        return reservaRepository.findByClaseIdAndUsuarioEmail(idClase, email);
    }



    public void eliminar(Reserva reserva) {
        reservaRepository.delete(reserva);
    }

public List<Long> getIdsClasesReservadasPorCorreo(String correo) {
    List<Reserva> reservas = reservaRepository.findByUsuarioEmail(correo);
    return reservas.stream().map(r -> r.getClase().getId()).toList();
}


    
}
