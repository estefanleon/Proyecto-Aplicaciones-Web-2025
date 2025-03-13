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

    public List<Entrenador> getEntrenadores() {
        return entrenadorRepository.findAll();
    }
}
