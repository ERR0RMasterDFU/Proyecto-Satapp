package com.salesianostriana.dam.proyecto_satapp.dto.categoria;

import com.salesianostriana.dam.proyecto_satapp.models.Categoria;

public record GetCategoriaBasicaDto(
        Long id,
        String nombre
) {

    public static GetCategoriaBasicaDto of(Categoria c) {
        return new GetCategoriaBasicaDto(
                c.getId(),
                c.getNombre()
        );
    }
}
