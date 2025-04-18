package com.gym.controller;

import com.gym.domain.Ganancia;
import com.gym.domain.User;
import com.gym.service.GananciaService;
import com.gym.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/ganancia") // 🔹 todas las rutas comienzan con /ganancia
public class GananciaController {

    @Autowired
    private GananciaService gananciaService;

    @Autowired
    private UserService userService;

    // 🔹 Ruta: /ganancia/generar (no debe tener "ganancias" duplicado)
@GetMapping("/generar")
public String generarGanancias() {
    List<User> usuarios = userService.getAll();

    System.out.println("Generando ingresos...");

    for (User user : usuarios) {
        if (user.getMembresia() != null) {
            // Verificar si ya existe un ingreso para esta membresía en la fecha actual
            boolean existe = gananciaService.existeIngresoPorMembresiaYFecha(
                user.getMembresia().getId(),
                new Date()
            );

            if (!existe) {
                System.out.println("Usuario: " + user.getName() + ", membresía: " + user.getMembresia().getTipo());

                Ganancia g = new Ganancia();
                g.setMonto(user.getMembresia().getPrecio());
                g.setFecha(new Date());
                g.setMembresia(user.getMembresia());

                gananciaService.guardar(g);
            } else {
                System.out.println("Ya existe ingreso para la membresía del usuario: " + user.getName());
            }
        }
    }

    return "redirect:/ganancia/ganancias";
}


    // 🔹 Ruta: /ganancia/ganancias (muestra el HTML)
    @GetMapping("/ganancias")
    public String verGanancias(Model model) {
        List<Ganancia> ingresos = gananciaService.obtenerTodos();
        Double total = gananciaService.calcularGananciaTotal();

        model.addAttribute("ingresos", ingresos);
        model.addAttribute("totalGanancia", total);

        return "ganancia/ganancias"; // HTML ubicado en templates/ganancia/ganancias.html
    }
}
