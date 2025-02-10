package com.salesianostriana.dam.proyecto_satapp.dto.incidencia;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Estado;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;

import java.time.LocalDateTime;

public record GetIncidenciaDto(
        Long id,
        LocalDateTime fecha,
        String titulo,
        String descripcion,
        Estado estado,
        boolean urgencia,
        GetCategoriaBasicaDto categoria,
        GetUsuarioBasicoDto usuario,
        GetEquipoBasicoDto equipo,
        GetUbicacionSinListasDto ubicacion
) {
    public static GetIncidenciaDto of(Incidencia i) {
        return new GetIncidenciaDto(
                i.getId(),
                i.getFecha(),
                i.getTitulo(),
                i.getDescripcion(),
                i.getEstado(),
                i.isUrgencia(),
                GetCategoriaBasicaDto.of(i.getCategoria()),
                GetUsuarioBasicoDto.of(i.getUsuario()),
                i.getEquipo() != null ? GetEquipoBasicoDto.of(i.getEquipo()) : null,
                i.getUbicacion() != null ? GetUbicacionSinListasDto.of(i.getUbicacion()) : null
        );
    }
}
