package com.salesianostriana.dam.proyecto_satapp.dto.usuarios;

public record EditUsuarioCmd(
        String nombre,
        String username,
        String password,
        String email,
        String role
) {
}
