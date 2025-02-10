package com.salesianostriana.dam.proyecto_satapp.dto.nota;

public record CreateNotaCmd(
        Long incidenciaId,
        Long tecnicoId,
        String contenido
) {
}
