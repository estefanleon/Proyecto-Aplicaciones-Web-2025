package com.gym.controller;

import com.gym.domain.Equipo;
import com.gym.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/equipo")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    // ✅ Listado de equipos con datos para fragmentos
    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = equipoService.getAllEquipos();
        model.addAttribute("equipos", lista);
        model.addAttribute("totalEquipos", lista.size());
        model.addAttribute("equipo", new Equipo()); // Necesario para el modal de agregar
        return "/equipo/listado";
    }

    // ✅ Mostrar formulario de agregar equipo (vista tradicional)
    @GetMapping("/agregar")
    public String agregar(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "/equipo/modificar";
    }

    // ✅ Guardar equipo (crear o actualizar)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("equipo") Equipo equipo) {
        equipoService.save(equipo);
        return "redirect:/equipo/listado";
    }

    // ✅ Editar equipo existente
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Long id, Model model) {
        Equipo equipo = equipoService.getEquipoById(id);
        if (equipo != null) {
            model.addAttribute("equipo", equipo);
            return "equipo/modificar";
        }
        return "redirect:/equipo/listado";
    }

    // ✅ Eliminar equipo
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        equipoService.delete(id);
        return "redirect:/equipo/listado";
    }
}
