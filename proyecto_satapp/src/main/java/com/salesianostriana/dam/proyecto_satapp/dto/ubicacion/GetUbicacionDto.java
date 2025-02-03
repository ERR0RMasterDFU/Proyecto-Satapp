package com.salesianostriana.dam.proyecto_satapp.dto.ubicacion;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;

import java.util.List;

public record GetUbicacionDto(
        Long id,
        String nombre,
        List<GetEquipoBasicoDto> listaEquipos
        //Lista<GetIncidenciaDto> listaIncidencias
) {
    public static GetUbicacionDto of(Ubicacion u, List<GetEquipoBasicoDto> listaEquipos
            //, List<GetIncidenciaDto> listaIncidencias
                                     ) {
        return new GetUbicacionDto(
                u.getId(),
                u.getNombre(),
                listaEquipos
                //, listaIncidencias
        );
    }
}
