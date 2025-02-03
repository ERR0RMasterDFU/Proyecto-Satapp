package com.salesianostriana.dam.proyecto_satapp.dto.equipo;

import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;

public record GetEquipoDto(
    String nombre,
    String caracteristicas,
    //GetUbicacionSinListasDto ubicacion
    String ubicacion
    //, GetIncidenciasDto listaIncidencias
){
    public static GetEquipoSinListasDto of(Equipo e
                                           //, List<GetIncidenciaSinListasDto> listaIncidencias
    ){
        return new GetEquipoSinListasDto(
                e.getNombre(),
                e.getCaracteristicas(),
                //GetUbicacionSinListasDto.of(e.getUbicacion())
                e.getUbicacion().getNombre()
                //listaIncidencias
        );
    }
}
