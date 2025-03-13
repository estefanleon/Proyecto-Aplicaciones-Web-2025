package com.gym.controller;

import com.gym.domain.User;
import com.gym.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // 📌 Mostrar la página de login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Redirige a login.html
    }

    // 📌 Procesar el inicio de sesión
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        // Buscar usuario por email y contraseña
        User usuarioAutenticado = userService.getUsers().stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);

if (usuarioAutenticado != null) {
    session.setAttribute("usuario", usuarioAutenticado); // ✅ Guardar usuario en sesión
    System.out.println("Usuario autenticado: " + usuarioAutenticado.getRole()); // 🛠️ Debug
    return "redirect:/"; // 🔄 Redirige al inicio

        } else {
            model.addAttribute("error", "Credenciales incorrectas"); // ❌ Mostrar error
            return "login"; // 🔄 Vuelve a login.html
        }
    }

    // 📌 Cerrar sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate(); // ❌ Elimina la sesión
        return "redirect:/login"; // 🔄 Redirige al login
    }
}
