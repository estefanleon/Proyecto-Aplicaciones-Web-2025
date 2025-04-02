package com.gym.service;

import com.gym.domain.Membresia;
import com.gym.repository.MembresiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembresiaService {

    @Autowired
    private MembresiaRepository membresiaRepository;

    public List<Membresia> getMembresias() {
        return membresiaRepository.findAll();
    }

    public Membresia getMembresiaById(Long id) {
        return membresiaRepository.findById(id).orElse(null);
    }
}
