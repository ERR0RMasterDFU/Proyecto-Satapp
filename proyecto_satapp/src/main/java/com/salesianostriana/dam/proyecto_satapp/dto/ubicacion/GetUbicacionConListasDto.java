package com.salesianostriana.dam.proyecto_satapp.dto.ubicacion;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoConUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;

import java.util.List;

public record GetUbicacionConListasDto (
        Long id,
        String nombre,
        List<GetEquipoDto> listaEquipos
) {
    public static GetUbicacionConListasDto of(Ubicacion u) {
        return new GetUbicacionConListasDto(
                u.getId(),
                u.getNombre(),
                u.getListaEquipos().stream().map(GetEquipoDto::of).toList() //CAMBIAR A CONSULTA
        );
    }
}
