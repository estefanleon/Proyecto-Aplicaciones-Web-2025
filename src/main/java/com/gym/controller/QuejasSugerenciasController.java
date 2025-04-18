package com.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuejasSugerenciasController {

    @GetMapping("/QuejasSugerencias")
    public String mostrarQuejasSugerencias() {
        return "QuejasSugerencias";
    }

    @PostMapping("confirmacionqueja")
    public String procesarFormulario() {
        return "confirmacionqueja";
    }
}