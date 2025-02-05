package com.salesianostriana.dam.proyecto_satapp.dto.equipo;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;

import java.util.List;

public record GetEquipoDto(
        Long id,
        String nombre,
        String caracteristicas,
        GetUbicacionSinListasDto ubicacion,
        List<GetIncidenciaBasicaDto> listaIncidencias
){
    public static GetEquipoDto of(Equipo e, List<GetIncidenciaBasicaDto> listaIncidencias){
        return new GetEquipoDto(
                e.getId(),
                e.getNombre(),
                e.getCaracteristicas(),
                GetUbicacionSinListasDto.of(e.getUbicacion()),
                listaIncidencias
        );
    }
}
