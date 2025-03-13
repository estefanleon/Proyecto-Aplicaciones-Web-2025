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

    // 📌 Agregar nuevo usuario (formulario)
    @GetMapping("/agregar")
    public String agregar(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/user/modifica";
    }

    // 📌 Guardar usuario (nuevo o modificado)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/user/listado";
    }

    // 📌 Modificar usuario (cargar datos en el formulario)
    @GetMapping("/modificar/{idUser}")
    public String modificar(@PathVariable("idUser") Long id, Model model) {
        User user = userService.getUser(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "/user/modifica";
        }
        return "redirect:/user/listado"; // Si el usuario no existe, redirigir
    }

    // 📌 Eliminar usuario
    @GetMapping("/eliminar/{idUser}")
    public String eliminar(@PathVariable("idUser") Long id) {
        userService.deleteUser(id);
        return "redirect:/user/listado";
    }
}
