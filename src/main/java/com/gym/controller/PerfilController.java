package com.gym.controller;

import com.gym.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilController {

    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session, Model model) {
        User usuario = (User) session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login"; // Redirige a login si no está autenticado
        }

        model.addAttribute("usuario", usuario);
        return "perfil"; // Renderiza perfil.html
    }
}
