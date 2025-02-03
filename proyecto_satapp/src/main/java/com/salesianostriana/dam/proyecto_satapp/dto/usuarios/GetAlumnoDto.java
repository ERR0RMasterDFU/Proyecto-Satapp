package com.salesianostriana.dam.proyecto_satapp.dto.usuarios;

import com.salesianostriana.dam.proyecto_satapp.models.Alumno;

public record GetAlumnoDto(
        String nombre,
        String username,
        String password,
        String email,
        String role
) {
    public static GetAlumnoDto of(Alumno alumno) {
        return new GetAlumnoDto(
                alumno.getNombre(),
                alumno.getUsername(),
                alumno.getPassword(),
                alumno.getEmail(),
                alumno.getRole()
        );
    }
}
