package com.gym.controller;

import com.gym.domain.Clase;
import com.gym.domain.User;
import com.gym.service.ClaseService;
import com.gym.service.ReservaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    @Autowired
    private ReservaService reservaService; // ✅ Inyección agregada

    @GetMapping("/clases")
    public String listarClases(Model model, HttpSession session) {
        List<Clase> clases = claseService.getClases();
        model.addAttribute("clases", clases);

        User usuario = (User) session.getAttribute("usuario");
        if (usuario != null) {
            List<Long> clasesReservadas = reservaService.getIdsClasesReservadasPorCorreo(usuario.getEmail());
            model.addAttribute("clasesReservadas", clasesReservadas); // ✅ importante
        }

        return "clases";
    }
}
