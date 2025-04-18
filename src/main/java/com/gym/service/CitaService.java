package com.gym.service;

import com.gym.domain.Cita;
import com.gym.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;

    public List<Cita> getAll() {
        return citaRepository.findAll();
    }



    public Cita getById(Long id) {
        return citaRepository.findById(id).orElse(null);
    }


    
    // ✅ Guardar nueva cita o actualizar
    public void guardar(Cita cita) {
        citaRepository.save(cita);
    }


    // ✅ Eliminar una cita por ID
    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }
    
    
    
    
}

