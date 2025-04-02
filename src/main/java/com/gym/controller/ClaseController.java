package com.gym.controller;

import com.gym.service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    @GetMapping("/clases")
    public String mostrarClases(Model model) {
        model.addAttribute("clases", claseService.getClases());
        return "clases";
    }
}
