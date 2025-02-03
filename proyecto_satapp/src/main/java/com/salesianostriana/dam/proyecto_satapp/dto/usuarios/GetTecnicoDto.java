package com.salesianostriana.dam.proyecto_satapp.dto.usuarios;

import com.salesianostriana.dam.proyecto_satapp.models.Tecnico;

public record GetTecnicoDto(
        String nombre,
        String username,
        String password,
        String email,
        String role
) {
    public static GetTecnicoDto of(Tecnico tecnico) {
        return new GetTecnicoDto(
                tecnico.getNombre(),
                tecnico.getUsername(),
                tecnico.getPassword(),
                tecnico.getEmail(),
                tecnico.getRole()
        );
    }
}
