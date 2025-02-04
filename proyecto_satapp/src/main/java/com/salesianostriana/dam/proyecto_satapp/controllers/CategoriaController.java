package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.EditCatgeoriaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.models.Categoria;
import com.salesianostriana.dam.proyecto_satapp.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtener todas las categorías")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorias obtenido correctamente.",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = GetCategoriaDto.class)))
    })
    @GetMapping("")
    public List<GetCategoriaSinListasDto> getAll() {
        return categoriaService.findAll();
    }

    @Operation(summary = "Obtener una categoría por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetCategoriaDto.class))),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada",
                content = @Content)
    })
    @GetMapping("/{id}")
    public GetCategoriaDto getById(@PathVariable Long id) {
        return categoriaService.findById(id);
    }

    @Operation(summary = "Crear una nueva categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria creada correctamente.",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EditCatgeoriaCmd.class)))
    })
    @PostMapping("")
    public ResponseEntity<Categoria> create(@RequestBody EditCatgeoriaCmd nuevoCategoriaCmd) {
        Categoria nuevaCategoria = categoriaService.save(nuevoCategoriaCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @Operation(summary = "Editar una categoría existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria editada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoriaDto.class))),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public GetCategoriaDto edit(@RequestBody EditCatgeoriaCmd aEditar, @PathVariable Long id) {
        return categoriaService.edit(aEditar, id);
    }
}
