package com.gym.controller;

import com.gym.domain.User;
import com.gym.service.MembresiaService;
import com.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MatriculaController {

    @Autowired
    private UserService userService;

    @Autowired
    private MembresiaService membresiaService;

    @GetMapping("/matricula")
    public String mostrarMatricula(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("membresias", membresiaService.getMembresias());
        return "matricula";
    }

    @PostMapping("/matricula")
    public String registrarUsuario(@ModelAttribute("user") User user, Model model) {
        try {
            userService.save(user);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("user", user);
            model.addAttribute("membresias", membresiaService.getMembresias());
            model.addAttribute("error", e.getMessage());
            return "matricula";
        }
    }
}
