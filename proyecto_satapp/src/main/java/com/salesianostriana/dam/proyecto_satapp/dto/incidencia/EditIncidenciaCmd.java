package com.salesianostriana.dam.proyecto_satapp.dto.incidencia;

import com.salesianostriana.dam.proyecto_satapp.models.Estado;

import java.time.LocalDateTime;

public record EditIncidenciaCmd(
        String titulo,
        String descripcion,
        Estado estado,
        boolean urgencia,
        Long categoriaId,
        //Long usuarioId,
        Long equipoId,
        Long ubicacionId
) {
}
