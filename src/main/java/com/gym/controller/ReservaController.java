package com.gym.controller;

import com.gym.domain.Clase;
import com.gym.domain.Reserva;
import com.gym.domain.User;
import com.gym.service.ClaseService;
import com.gym.service.ReservaService;
import com.gym.service.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReservaController {

    @Autowired
    private ClaseService claseService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UserService userService;
    



    // ✅ Mostrar clases para reservar
    @GetMapping("/reservar")
    public String listarClasesParaReservar(Model model) {
        List<Clase> clases = claseService.getClases();
        model.addAttribute("clases", clases);
        return "reservas";
    }

    // ✅ Mostrar formulario para reservar una clase
    @GetMapping("/reservar/{id}")
    public String mostrarFormularioReserva(@PathVariable("id") Long idClase, Model model) {
        Clase clase = claseService.getClaseById(idClase);
        Reserva reserva = new Reserva();
        reserva.setClase(clase);
        model.addAttribute("reserva", reserva);
        return "reservar";
    }
/*
    // ✅ Guardar reserva sin autenticación
@PostMapping("/guardarReserva")
public String guardarReserva(@ModelAttribute("reserva") Reserva reserva,
                             @RequestParam("correo") String correo,
                             RedirectAttributes redirectAttributes) {

    reserva.setFecha(LocalDate.now());

    User usuario = userService.getByEmail(correo); // usar el correo recibido
    reserva.setUsuario(usuario);
    reservaService.guardar(reserva);

    redirectAttributes.addFlashAttribute("mensaje", "¡Reserva realizada con éxito!");
    redirectAttributes.addFlashAttribute("tipo", "success");

    return "redirect:/clases";
}

*/
    
@PostMapping("/guardarReserva")
public String guardarReserva(@ModelAttribute("reserva") Reserva reserva,
                             @RequestParam("correo") String correo,
                             RedirectAttributes redirectAttributes) {
    
    reserva.setFecha(LocalDate.now());

    User usuario = userService.getByEmail(correo);
    reserva.setUsuario(usuario);

    // Recuperar clase desde la BD
    Clase claseCompleta = claseService.getClaseById(reserva.getClase().getId());
    reserva.setClase(claseCompleta);      // asignar clase completa a la reserva

    reservaService.guardar(reserva);      // guardar reserva
    claseService.save(claseCompleta);     // guardar clase con estado actualizado

    redirectAttributes.addFlashAttribute("mensaje", "¡Reserva realizada con éxito!");
    redirectAttributes.addFlashAttribute("tipo", "success");

    return "redirect:/clases";
}




@PostMapping("/cancelarReserva")
public String cancelarReserva(@RequestParam("idClase") Long idClase,
                              @RequestParam("correo") String correo,
                              RedirectAttributes redirectAttributes) {

    // Buscar la reserva por clase y correo
    Reserva reserva = reservaService.findByClaseIdAndUsuarioEmail(idClase, correo);

    if (reserva != null) {
        reservaService.eliminar(reserva); // Elimina la reserva

        Clase clase = claseService.getClaseById(idClase);
        claseService.save(clase); // Guarda el cambio

        redirectAttributes.addFlashAttribute("mensaje", "Reserva cancelada correctamente.");
        redirectAttributes.addFlashAttribute("tipo", "warning");
    } else {
        redirectAttributes.addFlashAttribute("mensaje", "No se encontró la reserva para cancelar.");
        redirectAttributes.addFlashAttribute("tipo", "danger");
    }

    return "redirect:/clases";
}




}
