package com.salesianostriana.dam.proyecto_satapp.dto.equipo;

import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;

public record GetEquipoSinListasDto(
        Long id,
        String nombre,
        String caracteristicas,
        GetUbicacionSinListasDto ubicacion
) {
    public static GetEquipoSinListasDto of(Equipo e) {
        return new GetEquipoSinListasDto(
                e.getId(),
                e.getNombre(),
                e.getCaracteristicas(),
                GetUbicacionSinListasDto.of(e.getUbicacion())
        );
    }

    // CONSTRUCTOR PARA CONSULTAS
    public GetEquipoSinListasDto(Long id, String nombre, String caracteristicas, Ubicacion ubicacion) {
        this(id, nombre, caracteristicas, GetUbicacionSinListasDto.of(ubicacion));
    }
}
