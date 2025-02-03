package com.salesianostriana.dam.proyecto_satapp.dto.incidencia;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Estado;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;

import java.time.LocalDate;

public record GetIncidenciaBasicaDto(
        Long id,
        LocalDate fecha,
        String titulo,
        String descripcion,
        Estado estado,
        boolean urgencia,
        //GetSubCategoriaDto categoria,
        String categoria
){
    public static GetIncidenciaBasicaDto of(Incidencia i) {
        return new GetIncidenciaBasicaDto(
                i.getId(),
                i.getFecha(),
                i.getTitulo(),
                i.getDescripcion(),
                i.getEstado(),
                i.isUrgencia(),
                //GetSubCategoriaDto.of(i.getCategoria()),
                i.getCategoria().getNombre()
        );
    }
}
