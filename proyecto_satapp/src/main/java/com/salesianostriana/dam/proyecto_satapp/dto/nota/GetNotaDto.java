package com.salesianostriana.dam.proyecto_satapp.dto.nota;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Categoria;
import com.salesianostriana.dam.proyecto_satapp.models.Estado;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;
import com.salesianostriana.dam.proyecto_satapp.models.Nota;

import java.time.LocalDateTime;

public record GetNotaDto(
    LocalDateTime fecha,
    String autor,
    String contenido,
    GetIncidenciaSinCategoriaDto incidencia
){
    public static GetNotaDto of(Nota n) {
        return new GetNotaDto(
                n.getFecha(),
                n.getAutor(),
                n.getContenido(),
                GetIncidenciaSinCategoriaDto.of(n.getIncidencia())
        );
    }

    // CONSTRUCTOR PARA CONSULTAS
    public GetNotaDto(LocalDateTime fecha, String autor, String contenido, Incidencia incidencia) {
        this(fecha, autor, contenido, GetIncidenciaSinCategoriaDto.of(incidencia));
    }
}
