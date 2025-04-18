package com.gym.controller;

import com.gym.service.entrenamientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class entrenamientosController {

    @Autowired
    private entrenamientosService entrenamientoService;

    @GetMapping("/entrenamientos")
    public String verEntrenamientos(Model model) {
        long total = entrenamientoService.contarTotal();
        long completados = entrenamientoService.contarCompletados();
        int porcentaje = total > 0 ? (int) ((double) completados / total * 100) : 0;

        model.addAttribute("total", total);
        model.addAttribute("completados", completados);
        model.addAttribute("porcentaje", porcentaje);

        return "entrenamientos"; 
    }
}
