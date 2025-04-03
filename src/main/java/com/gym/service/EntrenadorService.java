package com.gym.service;

import com.gym.domain.Entrenador;
import com.gym.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    public List<Entrenador> getAll() {
        return entrenadorRepository.findAll();
    }

    public void save(Entrenador entrenador) {
        entrenadorRepository.save(entrenador);
    }

    public Entrenador getById(Long id) {
        return entrenadorRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        entrenadorRepository.deleteById(id);
    }
}
