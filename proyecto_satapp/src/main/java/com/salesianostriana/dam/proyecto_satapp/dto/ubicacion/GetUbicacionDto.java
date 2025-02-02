package com.salesianostriana.dam.proyecto_satapp.dto.ubicacion;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;

import java.util.List;

public record GetUbicacionDto(
        String nombre,
        List<GetEquipoBasicoDto> listaEquipos
) {
    public static GetUbicacionDto of(Ubicacion u, List<GetEquipoBasicoDto> listaEquipos
            //, List<DtoIncidencias> listaIncidencias
                                     ) {
        return new GetUbicacionDto(
                u.getNombre(),
                listaEquipos
                //, listaIncidencias
        );
    }
}
