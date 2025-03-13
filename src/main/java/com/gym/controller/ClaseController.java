package com.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clase")
public class ClaseController {

    @GetMapping("/listado")
    public String listado(Model model) {
        // Aquí puedes agregar lógica para obtener clases si las tienes en la base de datos
        return "clases"; // Asegúrate de que este nombre coincide con `clases.html` en templates
    }
}
