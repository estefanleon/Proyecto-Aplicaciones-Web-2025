package com.gym.repository;

import com.gym.domain.Ganancia;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngresoRepository extends JpaRepository<Ganancia, Long> {
    
    boolean existsByMembresiaIdAndFecha(Long membresiaId, Date fecha);

}
