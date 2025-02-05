package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.EditCatgeoriaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Categoria;
import com.salesianostriana.dam.proyecto_satapp.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;


    @GetMapping("")
    public List<GetCategoriaSinListasDto> getAll() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    public GetCategoriaDto getById(@PathVariable Long id) {
        List<GetCategoriaBasicaDto> listaSubCategorias =
                categoriaService.getSubCategoriasDtoById(id);
        List<GetIncidenciaSinCategoriaDto> listaIncidencias =
                categoriaService.getIncidenciasByCategoriaId(id);
        Categoria categoria = categoriaService.findById(id);

        return GetCategoriaDto.of(categoria, listaSubCategorias, listaIncidencias);
    }

    @PostMapping("")
    public ResponseEntity<Categoria> create(@RequestBody EditCatgeoriaCmd nuevoCategoriaCmd) {
        Categoria nuevaCategoria = categoriaService.save(nuevoCategoriaCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public GetCategoriaDto edit(@RequestBody EditCatgeoriaCmd aEditar, @PathVariable Long id) {
        List<GetCategoriaBasicaDto> listaSubCategorias =
                categoriaService.getSubCategoriasDtoById(id);
        List<GetIncidenciaSinCategoriaDto> listaIncidencias =
                categoriaService.getIncidenciasByCategoriaId(id);
        Categoria categoria = categoriaService.edit(aEditar, id);

        return GetCategoriaDto.of(categoria, listaSubCategorias, listaIncidencias);
    }

}
