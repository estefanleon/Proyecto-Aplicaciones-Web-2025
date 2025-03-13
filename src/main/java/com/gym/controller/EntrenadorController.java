package com.gym.controller;

import com.gym.domain.Entrenador;
import com.gym.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @GetMapping("/entrenadores")
    public String listarEntrenadores(Model model) {
        List<Entrenador> listaEntrenadores = entrenadorService.getEntrenadores();
        model.addAttribute("entrenadores", listaEntrenadores);
        return "entrenadores";
    }
}
