package com.gym.controller;

import com.gym.domain.User;
import com.gym.service.SesionEntrenamientoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistorialController {

    @Autowired
    private SesionEntrenamientoService entrenamientoService;

    @GetMapping("/historial")
    public String verHistorial(Model model, HttpSession session) {
        User usuario = (User) session.getAttribute("usuario");

        if (usuario != null) {
            model.addAttribute("entrenamientos", entrenamientoService.getEntrenamientosPorUsuario(usuario));
            return "historial";
        }

        return "redirect:/login";
    }
}
