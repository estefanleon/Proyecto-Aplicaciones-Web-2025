package com.gym.controller;

import com.gym.domain.Plan;
import com.gym.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MatriculaController {

    @Autowired
    private PlanService planService;

    @Value("${tcambio}")
    private double tCambio;

    @GetMapping("/matricula")
    public String mostrarFormularioMatricula(Model model) {
        model.addAttribute("planes", planService.findAll());
        return "matricula"; // carga templates/matricula.html
    }

    @GetMapping("/matricula/resumen")
    public String mostrarResumen(@RequestParam("id") Long id, Model model) {
        Plan plan = planService.getPlanPorId(id);
        double totalColones = plan.getPrecio();
        double totalDolares = Math.round((totalColones / tCambio) * 100.0) / 100.0;

        model.addAttribute("plan", plan);
        model.addAttribute("totalDolares", totalDolares);
        model.addAttribute("precioVenta", tCambio);
        return "matricula/resumenPago"; // carga templates/matricula/resumenPago.html
    }
}
