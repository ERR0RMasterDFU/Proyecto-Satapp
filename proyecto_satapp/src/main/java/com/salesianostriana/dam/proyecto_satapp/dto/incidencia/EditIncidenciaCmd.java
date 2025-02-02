package com.salesianostriana.dam.proyecto_satapp.dto.incidencia;

import com.salesianostriana.dam.proyecto_satapp.models.Estado;

import java.time.LocalDate;

public record EditIncidenciaCmd(
        LocalDate fecha,
        String titulo,
        String descripcion,
        Estado estado,
        boolean urgencia,
        Long categoriaId,
        Long usuarioId,
        Long equipoId,
        Long ubicacionId
) {
}
