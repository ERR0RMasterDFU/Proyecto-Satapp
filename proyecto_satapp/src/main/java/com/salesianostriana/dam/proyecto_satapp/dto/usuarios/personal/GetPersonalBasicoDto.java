package com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal;

import com.salesianostriana.dam.proyecto_satapp.models.Personal;
import com.salesianostriana.dam.proyecto_satapp.models.Tipo;

public record GetPersonalBasicoDto(
        Long id,
        String nombre,
        String username,
        String email,
        String role,
        Tipo tipo
) {
    public static GetPersonalBasicoDto of(Personal p) {
        return new GetPersonalBasicoDto(
                p.getId(),
                p.getNombre(),
                p.getUsername(),
                p.getEmail(),
                p.getRole(),
                p.getTipo()
        );
    }
}