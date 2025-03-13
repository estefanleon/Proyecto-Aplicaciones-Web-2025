package com.gym.service;

import com.gym.domain.User;
import com.gym.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // 📌 Obtener todos los usuarios
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // 📌 Obtener un usuario por ID
    @Transactional(readOnly = true)
    public User getUser(User user) {
        return userRepository.findById(user.getId()).orElse(null);
    }
    
    // 📌 Eliminar un usuario
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }
    
    // 📌 Guardar o actualizar un usuario
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
