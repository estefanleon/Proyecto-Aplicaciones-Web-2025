package com.gym.controller;

import com.gym.service.ReservaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // 📌 Mostrar la lista de clases disponibles
    @GetMapping
    public String listarClases(Model model) {
        var clases = reservaService.getClasesDisponibles();
        model.addAttribute("clases", clases);
        return "reserva"; // 🔄 Cargar la vista de reservas
    }

    // 📌 Procesar la reserva de una clase por ID
    @PostMapping("/reservar/{idClase}")
    public String reservarClase(@PathVariable Long idClase, HttpSession session, RedirectAttributes redirectAttributes) {
        String usuario = (String) session.getAttribute("usuario"); // ✅ Obtener usuario de sesión

        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "Debes iniciar sesión para reservar una clase.");
            return "redirect:/login";
        }

        boolean reservaExitosa = reservaService.reservarClase(usuario, idClase);
        if (reservaExitosa) {
            redirectAttributes.addFlashAttribute("success", "Clase reservada con éxito.");
        } else {
            redirectAttributes.addFlashAttribute("error", "La clase ya está reservada o no existe.");
        }

        return "redirect:/reservas"; // 🔄 Volver a la lista de reservas
    }
}
