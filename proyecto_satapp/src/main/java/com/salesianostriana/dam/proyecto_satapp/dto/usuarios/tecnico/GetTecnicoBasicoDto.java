package com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico;

import com.salesianostriana.dam.proyecto_satapp.models.Tecnico;

public record GetTecnicoBasicoDto(
        Long id,
        String nombre,
        String username,
        String email,
        String role
) {
    public static GetTecnicoBasicoDto of(Tecnico t) {
        return new GetTecnicoBasicoDto(
                t.getId(),
                t.getNombre(),
                t.getUsername(),
                t.getEmail(),
                t.getRole()
        );
    }
}