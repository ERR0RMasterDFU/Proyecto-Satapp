package com.salesianostriana.dam.proyecto_satapp.dto.usuarios;

import com.salesianostriana.dam.proyecto_satapp.models.Usuario;

public record GetUsuarioDto(
        String nombre,
        String username,
        String password,
        String email,
        String role
) {
    public static GetUsuarioDto of(Usuario usuario) {
        return new GetUsuarioDto(
                usuario.getNombre(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
