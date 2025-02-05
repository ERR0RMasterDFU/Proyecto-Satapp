package com.salesianostriana.dam.proyecto_satapp.dto.incidencia;

import com.salesianostriana.dam.proyecto_satapp.models.Estado;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;

import java.time.LocalDateTime;

public record GetIncidenciaSinCategoriaDto(
        Long id,
        LocalDateTime fecha,
        String titulo,
        String descripcion,
        Estado estado,
        boolean urgencia
){
    public static GetIncidenciaSinCategoriaDto of(Incidencia i) {
        return new GetIncidenciaSinCategoriaDto(
                i.getId(),
                i.getFecha(),
                i.getTitulo(),
                i.getDescripcion(),
                i.getEstado(),
                i.isUrgencia()
        );
    }
}
