package com.salesianostriana.dam.proyecto_satapp.dto.equipo;

import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;

public record GetEquipoDto(
        Long id,
        String nombre,
        String caracteristicas,
        GetUbicacionSinListasDto ubicacion
        //, GetIncidenciasDto listaIncidencias
){
    public static GetEquipoDto of(Equipo e
                                           //, List<GetIncidenciaSinListasDto> listaIncidencias
    ){
        return new GetEquipoDto(
                e.getId(),
                e.getNombre(),
                e.getCaracteristicas(),
                GetUbicacionSinListasDto.of(e.getUbicacion())
                //listaIncidencias
        );
    }
}
