package com.salesianostriana.dam.proyecto_satapp.dto.incidencia;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Estado;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;

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
                GetEquipoBasicoDto.of(i.getEquipo()),
                GetUbicacionSinListasDto.of(i.getUbicacion())
        );
    }
}
