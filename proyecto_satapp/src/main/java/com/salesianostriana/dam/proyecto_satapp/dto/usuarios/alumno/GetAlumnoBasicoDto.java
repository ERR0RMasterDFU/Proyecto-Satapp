package com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno;

import com.salesianostriana.dam.proyecto_satapp.models.Alumno;

public record GetAlumnoBasicoDto(
        Long id,
        String nombre,
        String username,
        String email,
        String role
) {
    public static GetAlumnoBasicoDto of(Alumno a) {
        return new GetAlumnoBasicoDto(
                a.getId(),
                a.getNombre(),
                a.getUsername(),
                a.getEmail(),
                a.getRole()
        );
    }
}
