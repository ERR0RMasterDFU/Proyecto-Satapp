package com.salesianostriana.dam.proyecto_satapp.dto.usuarios;

public record EditTecnicoCmd(
        String nombre,
        String username,
        String password,
        String email,
        String role
) {
}
