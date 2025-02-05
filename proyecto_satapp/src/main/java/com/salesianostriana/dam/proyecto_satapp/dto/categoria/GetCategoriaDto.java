package com.salesianostriana.dam.proyecto_satapp.dto.categoria;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Categoria;

import java.util.List;

public record GetCategoriaDto(
        Long id,
        String nombre,
        String categoriaPadre,
        List<GetCategoriaBasicaDto> listaSubCategorias,
        List<GetIncidenciaSinCategoriaDto> listaIncidencias
) {
    public static GetCategoriaDto of(Categoria c,
                                     List<GetCategoriaBasicaDto> listaSubCategorias,
                                     List<GetIncidenciaSinCategoriaDto> listaIncidencias
    ) {
        String nombreCategoriaPadre = (c.getCategoriaPadre() != null) ?
                c.getCategoriaPadre().getNombre() : "Ninguna";

        return new GetCategoriaDto(
                c.getId(),
                c.getNombre(),
                nombreCategoriaPadre,
                listaSubCategorias,
                listaIncidencias
        );
    }
}
