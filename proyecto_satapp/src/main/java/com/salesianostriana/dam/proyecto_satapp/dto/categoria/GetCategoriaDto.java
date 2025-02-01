package com.salesianostriana.dam.proyecto_satapp.dto.categoria;

import com.salesianostriana.dam.proyecto_satapp.models.Categoria;

import java.util.List;

public record GetCategoriaDto(
        GetCategoriaSinListasDto categoria,
        List<GetSubCategoriaDto> listaSubCategorias
        //List<IncidenciasDto> listaIncidenciasDto
) {
    public static GetCategoriaDto of(
            Categoria c,
            List<GetSubCategoriaDto> listaSubCategorias
            //List<IncidenciasDto> listaIncidenciasDto
    ) {
        return new GetCategoriaDto(
                GetCategoriaSinListasDto.of(c),
                listaSubCategorias
        );
    }
}
