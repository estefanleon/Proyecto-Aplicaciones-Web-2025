package com.gym.controller;

import com.gym.domain.Cita;
import com.gym.domain.Cita.EstadoCita;
import com.gym.domain.User;
import com.gym.service.CitaService;
import com.gym.service.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cita")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private UserService userService;

    // ✅ Mostrar formulario para solicitar cita
    @GetMapping("/solicitar")
    public String mostrarFormularioCita(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("entrenadores", userService.getAllTrainers());
        return "cita/solicitar";
    }

    // ✅ Guardar cita
@PostMapping("/guardar")
public String guardarCita(@ModelAttribute Cita cita, HttpSession session) {
    // Obtener el usuario desde la sesión
    User usuario = (User) session.getAttribute("usuario");

    // Verificación por si no hay usuario autenticado
    if (usuario == null) {
        return "redirect:/login?error"; // o cualquier ruta de login que tengas
    }

    // Asignar datos automáticamente
    cita.setNombreCliente(usuario.getName());
    cita.setEmailCliente(usuario.getEmail());

    // Confirmación por consola
    System.out.println("Cliente: " + cita.getNombreCliente());
    System.out.println("Correo: " + cita.getEmailCliente());
    System.out.println("Fecha: " + cita.getFecha());
    System.out.println("Entrenador ID: " + cita.getEntrenador().getId());

    citaService.guardar(cita);
    return "cita/confirmacion"; // 👈 esto carga la vista de confirmación
}

    // ✅ Listar citas
    @GetMapping("/listado")
    public String listarCitas(Model model) {
        model.addAttribute("citas", citaService.getAll());
        return "cita/listado";
    }

    // ✅ Modificar cita (mostrar vista con datos cargados)
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, Model model) {
        Cita cita = citaService.getById(id);
        if (cita != null) {
            model.addAttribute("cita", cita);
            model.addAttribute("entrenadores", userService.getAllTrainers());
            return "cita/modificar";
        }
        return "redirect:/cita/listado";
    }

    // ✅ Eliminar cita
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return "redirect:/cita/listado";
    }
    
   


    @PostMapping("/aceptar")
public String aceptarCita(@RequestParam Long id) {
    Cita cita = citaService.getById(id);
    if (cita != null) {
        cita.setEstado(Cita.EstadoCita.ACEPTADA);
        citaService.guardar(cita);
    }
    return "redirect:/cita/listado";
}

@PostMapping("/rechazar")
public String rechazarCita(@RequestParam Long id) {
    Cita cita = citaService.getById(id);
    if (cita != null) {
        cita.setEstado(Cita.EstadoCita.RECHAZADA);
        citaService.guardar(cita);
    }
    return "redirect:/cita/listado";
}

    
}
