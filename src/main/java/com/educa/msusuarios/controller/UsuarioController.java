package com.educa.msusuarios.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @PostMapping("/registro")
    public String registrar(@RequestBody String usuario) {
        return "Usuario registrado correctamente";
    }

    @PostMapping("/login")
    public String login(@RequestBody String credenciales) {
        return "Login exitoso";
    }

    @GetMapping("/{id}")
    public String obtenerPerfil(@PathVariable Long id) {
        return "Perfil del usuario " + id;
    }
}
