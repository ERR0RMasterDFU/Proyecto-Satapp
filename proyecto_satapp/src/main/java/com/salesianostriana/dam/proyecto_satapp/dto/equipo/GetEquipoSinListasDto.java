package com.salesianostriana.dam.proyecto_satapp.dto.equipo;

import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;

public record GetEquipoSinListasDto(
        String nombre,
        String caracteristicas,
        //GetUbicacionSinListasDto ubicacion
        String ubicacion
) {
    public static GetEquipoSinListasDto of(Equipo e) {
        return new GetEquipoSinListasDto(
                e.getNombre(),
                e.getCaracteristicas(),
                //GetUbicacionSinListasDto.of(e.getUbicacion())
                e.getUbicacion().getNombre()
        );
    }

    /* CONSTRUCTOR PARA CONSULTAS
    public GetEquipoSinListasDto(String nombre, String caracteristicas, Ubicacion ubicacion) {
        this(nombre, caracteristicas, GetUbicacionSinListasDto.of(ubicacion));
    }*/
}
