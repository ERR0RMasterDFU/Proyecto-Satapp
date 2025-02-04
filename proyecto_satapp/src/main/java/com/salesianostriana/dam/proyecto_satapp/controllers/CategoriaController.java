package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.EditCatgeoriaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.Categoria;
import com.salesianostriana.dam.proyecto_satapp.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
@Tag(name = "Categorías", description = "Controlador para la gestión de categorías")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Obtener todas las categorías", description = "Devuelve una lista de todas las categorías sin listas de relaciones.")
    @GetMapping("")
    public List<GetCategoriaSinListasDto> getAll() {
        return categoriaService.findAll();
    }

    @Operation(summary = "Obtener una categoría por ID", description = "Devuelve los detalles de una categoría específica por su ID.")
    @GetMapping("/{id}")
    public GetCategoriaDto getById(@PathVariable Long id) {
        return categoriaService.findById(id);
    }

    @Operation(summary = "Crear una nueva categoría", description = "Crea una nueva categoría con los datos proporcionados en la solicitud.")
    @PostMapping("")
    public ResponseEntity<Categoria> create(@RequestBody EditCatgeoriaCmd nuevoCategoriaCmd) {
        Categoria nuevaCategoria = categoriaService.save(nuevoCategoriaCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @Operation(summary = "Editar una categoría existente", description = "Modifica los datos de una categoría ya existente mediante su ID.")
    @PutMapping("/{id}")
    public GetCategoriaDto edit(@RequestBody EditCatgeoriaCmd aEditar, @PathVariable Long id) {
        return categoriaService.edit(aEditar, id);
    }
}
