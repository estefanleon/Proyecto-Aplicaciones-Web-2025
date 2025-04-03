package com.gym.service;

import com.gym.domain.SesionEntrenamiento;
import com.gym.domain.User;
import com.gym.repository.SesionEntrenamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionEntrenamientoService {

    @Autowired
    private SesionEntrenamientoRepository repo;

    public List<SesionEntrenamiento> getEntrenamientosPorUsuario(User usuario) {
        return repo.findByUsuario(usuario);
    }

    public void guardar(SesionEntrenamiento sesion) {
        repo.save(sesion);
    }
}
