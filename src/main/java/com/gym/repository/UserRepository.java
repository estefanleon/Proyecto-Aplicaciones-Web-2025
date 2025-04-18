package com.gym.repository;
import java.util.List;

import com.gym.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
        List<User> findByRole(String role); // Esto devuelve usuarios con rol 'TRAINER'
List<User> findByMembresiaIsNotNull();

}