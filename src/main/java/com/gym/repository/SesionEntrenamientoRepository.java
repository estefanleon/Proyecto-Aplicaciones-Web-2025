package com.gym.repository;

import com.gym.domain.SesionEntrenamiento;
import com.gym.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SesionEntrenamientoRepository extends JpaRepository<SesionEntrenamiento, Long> {
    List<SesionEntrenamiento> findByUsuario(User usuario);
}
