package com.gym.controller;

import com.gym.domain.Membresia;
import com.gym.domain.User;
import com.gym.service.MembresiaService;
import com.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MatriculaController {

    @Autowired
    private UserService userService;

    @Autowired
    private MembresiaService membresiaService;

    // Mostrar formulario de matrícula (para ADMIN)
    @GetMapping("/matricula")
    public String mostrarMatricula(Model model) {
        model.addAttribute("correo", "");
        model.addAttribute("membresias", membresiaService.getMembresias());
        return "matricula";
    }

    // Procesar la asignación de membresía
    @PostMapping("/matricula")
    public String asignarMembresia(@RequestParam("correo") String correo,
                                   @RequestParam("membresiaId") Long membresiaId,
                                   Model model) {
        User usuario = userService.getByEmail(correo);
        if (usuario == null) {
            model.addAttribute("error", "El correo no está registrado.");
        } else {
            Membresia membresia = membresiaService.getMembresiaById(membresiaId);
            usuario.setMembresia(membresia);
            userService.save(usuario);
            model.addAttribute("mensaje", "Membresía asignada correctamente a " + usuario.getName());
        }

        // Recargar datos para el formulario
        model.addAttribute("correo", correo);
        model.addAttribute("membresias", membresiaService.getMembresias());
        return "matricula";
    }
}
