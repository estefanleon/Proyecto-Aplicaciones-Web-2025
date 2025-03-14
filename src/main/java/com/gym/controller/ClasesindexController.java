package com.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClasesindexController {

    @GetMapping("/clasesindex")
    public String mostrarContacto() {
        return "clasesindex"; // Retorna la vista "contacto.html"
    }
}
