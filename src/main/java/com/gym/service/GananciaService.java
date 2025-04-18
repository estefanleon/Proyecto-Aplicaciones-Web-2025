package com.gym.service;

import com.gym.domain.Ganancia;
import com.gym.domain.User;
import com.gym.repository.IngresoRepository;
import com.gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GananciaService {

    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private UserRepository userRepository;

    public void generarGananciasDesdeMembresias() {
        List<User> usuariosConMembresia = userRepository.findByMembresiaIsNotNull();

        for (User user : usuariosConMembresia) {
            Ganancia ingreso = new Ganancia();
            ingreso.setMonto(user.getMembresia().getPrecio());
            ingreso.setFecha(new Date()); // puedes ajustar aquí si deseas usar otra fecha

            ingresoRepository.save(ingreso);
        }
    }
    
    public boolean existeIngresoPorMembresiaYFecha(Long membresiaId, Date fecha) {
    return ingresoRepository.existsByMembresiaIdAndFecha(membresiaId, fecha);
}


    public List<Ganancia> obtenerTodos() {
        return ingresoRepository.findAll();
    }

    public double calcularTotal() {
        return ingresoRepository.findAll()
                .stream()
                .mapToDouble(Ganancia::getMonto)
                .sum();
    }
    public void guardar(Ganancia g) {
    ingresoRepository.save(g);
}

public Double calcularGananciaTotal() {
    List<Ganancia> ingresos = ingresoRepository.findAll();
    double total = 0;

    for (Ganancia ingreso : ingresos) {
        total += ingreso.getMonto();
    }

    return total;
}

    
}
