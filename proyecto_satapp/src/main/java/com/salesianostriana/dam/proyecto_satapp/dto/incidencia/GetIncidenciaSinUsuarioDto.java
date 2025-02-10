package com.salesianostriana.dam.proyecto_satapp.dto.incidencia;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.*;

import java.time.LocalDateTime;

public record GetIncidenciaSinUsuarioDto(
        Long id,
        LocalDateTime fecha,
        String titulo,
        String descripcion,
        Estado estado,
        boolean urgencia,
        GetCategoriaBasicaDto categoria,
        GetEquipoBasicoDto equipo,
        GetUbicacionSinListasDto ubicacion
) {
    public static GetIncidenciaSinUsuarioDto of(Incidencia i) {
        return new GetIncidenciaSinUsuarioDto(
                i.getId(),
                i.getFecha(),
                i.getTitulo(),
                i.getDescripcion(),
                i.getEstado(),
                i.isUrgencia(),
                GetCategoriaBasicaDto.of(i.getCategoria()),
                i.getEquipo() != null ? GetEquipoBasicoDto.of(i.getEquipo()) : null,
                i.getUbicacion() != null ? GetUbicacionSinListasDto.of(i.getUbicacion()) : null
        );
    }

    // CONSTRUCTOR PARA CONSULTAS
    public GetIncidenciaSinUsuarioDto(Long id, LocalDateTime fecha, String titulo, String descripcion,
                                      Estado estado, boolean urgencia, Categoria categoria,
                                      Equipo equipo, Ubicacion ubicacion) {
        this(id, fecha, titulo, descripcion, estado, urgencia, GetCategoriaBasicaDto.of(categoria),
                GetEquipoBasicoDto.of(equipo), GetUbicacionSinListasDto.of(ubicacion));
    }

}
