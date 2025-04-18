package com.gym.repository;

import com.gym.domain.entrenamientos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface entrenamientosRepository extends JpaRepository<entrenamientos, Long> {
    // Opcional: métodos personalizados
    long countByCompletadoTrue();
}

