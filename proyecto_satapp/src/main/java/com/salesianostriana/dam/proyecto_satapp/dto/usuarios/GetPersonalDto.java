package com.salesianostriana.dam.proyecto_satapp.dto.usuarios;


import com.salesianostriana.dam.proyecto_satapp.models.Personal;

public record GetPersonalDto(
        String nombre,
        String username,
        String password,
        String email,
        String role
) {
    public static GetPersonalDto of(Personal personal) {
        return new GetPersonalDto(
                personal.getNombre(),
                personal.getUsername(),
                personal.getPassword(),
                personal.getEmail(),
                personal.getRole()
        );
    }
}
