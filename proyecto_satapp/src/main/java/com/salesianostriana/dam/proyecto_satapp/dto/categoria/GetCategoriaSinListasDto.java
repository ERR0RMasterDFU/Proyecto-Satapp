package com.salesianostriana.dam.proyecto_satapp.dto.categoria;

import com.salesianostriana.dam.proyecto_satapp.models.Categoria;


public record GetCategoriaSinListasDto(
        String nombre,
        String categoriaPadre
) {

    public static GetCategoriaSinListasDto of(Categoria c) {

        String nombreCategoriaPadre = (c.getCategoriaPadre() != null) ?
                c.getCategoriaPadre().getNombre() : "Ninguna";

        return new GetCategoriaSinListasDto(
                c.getNombre(),
                nombreCategoriaPadre
        );
    }
}
