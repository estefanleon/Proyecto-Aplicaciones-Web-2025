package com.gym.service;

import com.gym.domain.Clase;
import com.gym.domain.Reserva;
import com.gym.repository.ClaseRepository;
import com.gym.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    // 📌 Obtener todas las clases disponibles
    public List<Clase> getClasesDisponibles() {
        return claseRepository.findAll();
    }

    // 📌 Método para reservar una clase
    public boolean reservarClase(String usuario, Long idClase) {
        Clase clase = claseRepository.findById(idClase).orElse(null);
        if (clase != null && !clase.isReservada()) {
            clase.setReservada(true); // ✅ Marcar como reservada
            claseRepository.save(clase);

            // ✅ Guardar la reserva en la base de datos
            Reserva reserva = new Reserva();
            reserva.setNombreUsuario(usuario);
            reserva.setNombreClase(clase.getNombreClase());
            reservaRepository.save(reserva);

            return true; // ✅ Reserva exitosa
        }
        return false; // ❌ No se pudo reservar (ya estaba reservada o no existe)
    }

    // 📌 Obtener todas las reservas realizadas
    public List<Reserva> getReservas() {
        return reservaRepository.findAll();
    }
}
