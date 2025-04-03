package com.gym.repository;

import com.gym.domain.Reserva;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT COUNT(r) > 0 FROM Reserva r WHERE r.clase.id = :claseId AND r.usuario.email = :email")
    boolean existeReservaPorClaseYUsuario(@Param("claseId") Long claseId, @Param("email") String email);

    @Query("SELECT r FROM Reserva r WHERE r.clase.id = :claseId AND r.usuario.email = :email")
    Reserva findByClaseIdAndUsuarioEmail(@Param("claseId") Long claseId, @Param("email") String email);
    
    @Query("SELECT r FROM Reserva r WHERE r.usuario.email = :email")
    List<Reserva> findByUsuarioEmail(@Param("email") String email);

}
