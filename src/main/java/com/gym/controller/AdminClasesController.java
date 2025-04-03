package com.gym.controller;

import com.gym.domain.Clase;
import com.gym.service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/clases")
public class AdminClasesController {

    @Autowired
    private ClaseService claseService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = claseService.getClases();
        model.addAttribute("clases", lista);
        model.addAttribute("totalClases", lista.size());
        model.addAttribute("clase", new Clase()); // ✅ Necesario si usas fragments
        return "admin/clases/listado";
    }

    @GetMapping("/agregar")
    public String agregar(Model model) {
        model.addAttribute("clase", new Clase());
        return "admin/clases/modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Clase clase) {
        claseService.save(clase);
        return "redirect:/admin/clases/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, Model model) {
        model.addAttribute("clase", claseService.getClaseById(id));
        return "admin/clases/modificar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        claseService.deleteClase(id);
        return "redirect:/admin/clases/listado";
    }
}
