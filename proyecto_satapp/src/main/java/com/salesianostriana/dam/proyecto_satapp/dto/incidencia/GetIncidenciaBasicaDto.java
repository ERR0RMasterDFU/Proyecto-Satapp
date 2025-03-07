package com.salesianostriana.dam.proyecto_satapp.dto.incidencia;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.Categoria;
import com.salesianostriana.dam.proyecto_satapp.models.Estado;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;

import java.time.LocalDateTime;

public record GetIncidenciaBasicaDto(
        Long id,
        LocalDateTime fecha,
        String titulo,
        String descripcion,
        Estado estado,
        boolean urgencia,
        GetCategoriaBasicaDto categoria
){
    public static GetIncidenciaBasicaDto of(Incidencia i) {
        return new GetIncidenciaBasicaDto(
                i.getId(),
                i.getFecha(),
                i.getTitulo(),
                i.getDescripcion(),
                i.getEstado(),
                i.isUrgencia(),
                GetCategoriaBasicaDto.of(i.getCategoria())
        );
    }

    // CONSTRUCTOR PARA CONSULTAS
    public GetIncidenciaBasicaDto(Long id, LocalDateTime nombre, String titulo, String descripcion,
                                  Estado estado, boolean urgencia, Categoria categoria) {
        this(id, nombre, titulo, descripcion, estado, urgencia, GetCategoriaBasicaDto.of(categoria));
    }
}
