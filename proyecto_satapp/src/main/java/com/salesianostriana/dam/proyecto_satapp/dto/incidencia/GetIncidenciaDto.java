package com.salesianostriana.dam.proyecto_satapp.dto.incidencia;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetSubCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.Estado;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;

import java.time.LocalDate;

public record GetIncidenciaDto(
        LocalDate fecha,
        String titulo,
        String descripcion,
        Estado estado,
        boolean urgencia,
        //GetSubCategoriaDto categoria,
        String categoria,
        Usuario usuario,
        GetEquipoBasicoDto equipo,
        //GetUbicacionSinListasDto ubicacion
        String ubicacion
) {
    public static GetIncidenciaDto of(Incidencia i) {

        String ubicacion = (i.getUbicacion() != null) ? i.getUbicacion().getNombre() : "Ninguna";

        return new GetIncidenciaDto(
                i.getFecha(),
                i.getTitulo(),
                i.getDescripcion(),
                i.getEstado(),
                i.isUrgencia(),
                //GetSubCategoriaDto.of(i.getCategoria()),
                i.getCategoria().getNombre(),
                i.getUsuario(),
                GetEquipoBasicoDto.of(i.getEquipo()),
                //GetUbicacionSinListasDto.of(i.getUbicacion()),
                ubicacion
        );
    }
}
