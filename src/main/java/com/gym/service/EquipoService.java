package com.gym.service;

import com.gym.domain.Equipo;
import com.gym.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    // Obtener todos los equipos
    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }

    // Obtener un equipo por ID
    public Equipo getEquipoById(Long id) {
        return equipoRepository.findById(id).orElse(null);
    }

    // Guardar o actualizar un equipo
    public void save(Equipo equipo) {
        equipoRepository.save(equipo);
    }

    // Eliminar equipo por ID
    public void delete(Long id) {
        equipoRepository.deleteById(id);
    }
}
