package com.gym.service;

import com.gym.domain.User;
import com.gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



import com.gym.domain.User;
import com.gym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    public void save(User user) {
        userRepository.save(user); // ya no lanza error, porque ahora es asignación, no creación
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    




    // ✅ Obtener todos los usuarios
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // ✅ Obtener usuarios con rol TRAINER (entrenadores)
public List<User> getAllTrainers() {
    return userRepository.findAll()
            .stream()
            .filter(user -> "TRAINER".equalsIgnoreCase(user.getRole().name()))
            .collect(Collectors.toList());
}




    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
        public List<User> getAllByRole(String role) {
        return userRepository.findByRole(role);
    }
        
        
        
        
        
}

    