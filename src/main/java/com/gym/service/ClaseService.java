package com.gym.service;

import com.gym.domain.Clase;
import com.gym.repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    public List<Clase> getClases() {
        return claseRepository.findAll();
    }

    public Clase getClaseById(Long id) {
        return claseRepository.findById(id).orElse(null);
    }

    // ✅ Método para guardar o actualizar una clase
    public void save(Clase clase) {
        claseRepository.save(clase);
    }
}
