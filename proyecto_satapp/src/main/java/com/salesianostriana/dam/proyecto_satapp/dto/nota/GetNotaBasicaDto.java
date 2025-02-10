package com.salesianostriana.dam.proyecto_satapp.dto.nota;

import com.salesianostriana.dam.proyecto_satapp.models.Nota;

import java.time.LocalDateTime;

public record GetNotaBasicaDto(
        LocalDateTime fecha,
        String autor,
        String contenido
){
    public static GetNotaBasicaDto of(Nota n) {
        return new GetNotaBasicaDto(
                n.getFecha(),
                n.getAutor(),
                n.getContenido()
        );
    }
}
