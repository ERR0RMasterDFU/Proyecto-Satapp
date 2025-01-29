package com.salesianostriana.dam.proyecto_satapp.dto;

import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;

public record GetUbicacionDto(
        Long id,
        String nombre
) {
    public static GetUbicacionDto of(Ubicacion u) {
        return new GetUbicacionDto(
                u.getId(),
                u.getNombre()
        );
    }
}
