package com.gym.controller;

import com.gym.domain.User;
import com.gym.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // 📌 Mostrar la página de login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Redirige a login.html
    }

    // 📌 Procesar el inicio de sesión
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        // Buscar usuario por email
        var users = userService.getUsers();
        User usuarioAutenticado = null;

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                usuarioAutenticado = user;
                break;
            }
        }

        if (usuarioAutenticado != null) {
            session.setAttribute("usuario", usuarioAutenticado); // Guardar usuario en sesión
            return "redirect:/"; // Redirige al inicio
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login"; // Vuelve a la página de login con error
        }
    }

    // 📌 Cerrar sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate(); // Elimina la sesión
        return "redirect:/login"; // Redirige al login
    }
}
