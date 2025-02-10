package com.salesianostriana.dam.proyecto_satapp.dto.nota;

public record EditNotaCmd(
        Long incidenciaId,
        Long tecnicoId,
        String contenido
) {
}
