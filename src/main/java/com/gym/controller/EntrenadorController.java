package com.gym.controller;

import com.gym.domain.Entrenador;
import com.gym.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    // ✅ Listado de entrenadores con datos para fragmentos
    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = entrenadorService.getAll();
        model.addAttribute("entrenadores", lista);
        model.addAttribute("totalEntrenadores", lista.size());
        model.addAttribute("entrenador", new Entrenador()); // Necesario para el modal de agregar
        return "entrenador/listado";
    }

    // ✅ Mostrar formulario de agregar entrenador (vía vista tradicional, no fragmento)
    @GetMapping("/agregar")
    public String agregar(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenador/modifica";
    }

    // ✅ Guardar entrenador (crear o actualizar)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("entrenador") Entrenador entrenador) {
        entrenadorService.save(entrenador);
        return "redirect:/entrenador/listado";
    }

    // ✅ Editar entrenador existente
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Long id, Model model) {
        Entrenador entrenador = entrenadorService.getById(id);
        if (entrenador != null) {
            model.addAttribute("entrenador", entrenador);
            return "entrenador/modifica";
        }
        return "redirect:/entrenador/listado";
    }

    // ✅ Eliminar entrenador
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        entrenadorService.deleteById(id);
        return "redirect:/entrenador/listado";
    }
}
