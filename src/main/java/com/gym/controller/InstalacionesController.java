package com.gym.controller;



 
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.GetMapping;
 
 @Controller
 public class InstalacionesController {
     
     @GetMapping("/instalaciones")
     public String mostrarInstalaciones() {
         return "instalaciones";
     }
 }


