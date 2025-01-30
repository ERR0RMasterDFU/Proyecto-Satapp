package com.salesianostriana.dam.proyecto_satapp.dto.equipo;

import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;

public record GetEquipoConUbicacionDto(
        Long id,
        String nombre,
        String caracteristicas,
        GetUbicacionDto ubicacion
) {
    public static GetEquipoConUbicacionDto of(Equipo e) {
        return new GetEquipoConUbicacionDto(
                e.getId(),
                e.getNombre(),
                e.getCaracteristicas(),
                GetUbicacionDto.of(e.getUbicacion())
        );
    }
}
