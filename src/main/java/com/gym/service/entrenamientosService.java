package com.gym.service;

import com.gym.domain.entrenamientos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.gym.repository.entrenamientosRepository;

@Service
public class entrenamientosService {

    @Autowired
    private entrenamientosRepository entrenamientoRepository;

    public List<entrenamientos> obtenerTodos() {
        return entrenamientoRepository.findAll();
    }

    public long contarCompletados() {
        return entrenamientoRepository.countByCompletadoTrue();
    }

    public long contarTotal() {
        return entrenamientoRepository.count();
    }
}
