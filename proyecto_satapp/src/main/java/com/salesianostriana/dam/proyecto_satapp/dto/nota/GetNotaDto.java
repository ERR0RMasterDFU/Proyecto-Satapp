package com.salesianostriana.dam.proyecto_satapp.dto.nota;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;
import com.salesianostriana.dam.proyecto_satapp.models.Nota;

import java.time.LocalDateTime;

public record GetNotaDto(
    LocalDateTime fecha,
    String autor,
    String contenido,
    GetIncidenciaBasicaDto incidencia
){
    public static GetNotaDto of(Nota n) {
        return new GetNotaDto(
                n.getFecha(),
                n.getAutor(),
                n.getContenido(),
                GetIncidenciaBasicaDto.of(n.getIncidencia())
        );
    }
}
