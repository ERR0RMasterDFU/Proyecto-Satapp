package com.salesianostriana.dam.proyecto_satapp.dto.categoria;

import com.salesianostriana.dam.proyecto_satapp.models.Categoria;

import java.util.List;

public record GetCategoriaDto(
        //GetCategoriaSinListasDto categoria,
        Long id,
        String nombre,
        String categoriaPadre,
        List<GetCategoriaBasicaDto> listaSubCategorias
        //List<IncidenciasDto> listaIncidenciasDto
) {
    public static GetCategoriaDto of(Categoria c,
            List<GetCategoriaBasicaDto> listaSubCategorias
            //List<IncidenciasDto> listaIncidenciasDto
    ) {
        String nombreCategoriaPadre = (c.getCategoriaPadre() != null) ?
                c.getCategoriaPadre().getNombre() : "Ninguna";

        return new GetCategoriaDto(
                c.getId(),
                c.getNombre(),
                nombreCategoriaPadre,
                //GetCategoriaSinListasDto.of(c),
                listaSubCategorias
        );
    }
}
