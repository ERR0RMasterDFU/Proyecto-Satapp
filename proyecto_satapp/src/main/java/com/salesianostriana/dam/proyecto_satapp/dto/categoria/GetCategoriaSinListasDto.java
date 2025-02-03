package com.salesianostriana.dam.proyecto_satapp.dto.categoria;

import com.salesianostriana.dam.proyecto_satapp.models.Categoria;


public record GetCategoriaSinListasDto(
        Long id,
        String nombre,
        String categoriaPadre
) {

    public static GetCategoriaSinListasDto of(Categoria c) {

        String nombreCategoriaPadre = (c.getCategoriaPadre() != null) ?
                c.getCategoriaPadre().getNombre() : "Ninguna";

        return new GetCategoriaSinListasDto(
                c.getId(),
                c.getNombre(),
                nombreCategoriaPadre
        );
    }
}
