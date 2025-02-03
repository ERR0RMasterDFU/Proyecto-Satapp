package com.salesianostriana.dam.proyecto_satapp.dto.ubicacion;

import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;

public record GetUbicacionSinListasDto(
        Long id,
        String nombre
) {
    public static GetUbicacionSinListasDto of(Ubicacion u) {
        return new GetUbicacionSinListasDto(
                u.getId(),
                u.getNombre()
        );
    }
}
