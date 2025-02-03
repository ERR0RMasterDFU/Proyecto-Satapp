package com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal;

import com.salesianostriana.dam.proyecto_satapp.models.Tipo;

public record EditPersonalCmd(
        String nombre,
        String username,
        String password,
        String email,
        String role,
        Tipo tipo
) {
}
