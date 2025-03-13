package com.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MatriculaController {

    @GetMapping("/matricula")
    public String mostrarMatricula() {
        return "matricula"; // Retorna la vista "matricula.html"
    }
}
