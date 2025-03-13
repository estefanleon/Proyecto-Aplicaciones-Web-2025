
package com.gym.controller;

import com.gym.domain.User;
import com.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 📌 Listar todos los usuarios
    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = userService.getUsers();
        model.addAttribute("users", lista);
        model.addAttribute("totalUsers", lista.size());
        return "/user/listado";
    }

    // 📌 Eliminar usuario
    @GetMapping("/eliminar/{idUser}")
    public String eliminar(User user) {
        userService.delete(user);
        return "redirect:/user/listado";
    }

    // 📌 Modificar usuario (cargar formulario con los datos)
    @GetMapping("/modificar/{idUser}")
    public String modificar(User user, Model model) {
        user = userService.getUser(user);
        model.addAttribute("user", user);
        return "/user/modifica";
    }

    // 📌 Guardar usuario (nuevo o modificado)
    @PostMapping("/guardar")
    public String guardar(User user) {
        userService.save(user);
        return "redirect:/user/listado";
    }
}

