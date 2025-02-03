package com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario;

import com.salesianostriana.dam.proyecto_satapp.models.Usuario;

public record GetUsuarioBasicoDto(
        Long id,
        String nombre,
        String username,
        String role
) {
    public static GetUsuarioBasicoDto of(Usuario u) {
        return new GetUsuarioBasicoDto(
                u.getId(),
                u.getNombre(),
                u.getUsername(),
                u.getRole()
        );
    }
}
