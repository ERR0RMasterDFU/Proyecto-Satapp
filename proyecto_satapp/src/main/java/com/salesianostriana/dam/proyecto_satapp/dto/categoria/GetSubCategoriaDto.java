package com.salesianostriana.dam.proyecto_satapp.dto.categoria;

import com.salesianostriana.dam.proyecto_satapp.models.Categoria;

public record GetSubCategoriaDto(
        String nombre
) {

    public static GetSubCategoriaDto of(Categoria c) {
        return new GetSubCategoriaDto(
                c.getNombre()
        );
    }
}
