package com.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // ✅ Esto hace que Spring reconozca esta clase como controlador
public class EntrenadorIndexController {
    
    @GetMapping("/EntrenadorIndex")
    public String mostrarEntrenadorIndex() {
        return "EntrenadorIndex"; 
    }
}
