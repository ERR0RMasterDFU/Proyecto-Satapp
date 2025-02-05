package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.EditCatgeoriaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Categoria;
import com.salesianostriana.dam.proyecto_satapp.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
@Tag(name = "Categorías", description = "Gestión de categorías del sistema")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(summary = "Obtener todas las categorías")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de categorías obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoriaSinListasDto.class),
                            examples = @ExampleObject(value = """
                                    [
                                        {
                                            "id": 1,
                                            "nombre": "ComputaciÃ³n y comunicaciÃ³n.",
                                            "categoriaPadre": "Ninguna"
                                        },
                                        {
                                            "id": 2,
                                            "nombre": "ElectrodomÃ©stico",
                                            "categoriaPadre": "Ninguna"
                                        },
                                        {
                                            "id": 3,
                                            "nombre": "Ordenador",
                                            "categoriaPadre": "ComputaciÃ³n y comunicaciÃ³n."
                                        },
                                        {
                                            "id": 4,
                                            "nombre": "Tablet",
                                            "categoriaPadre": "ComputaciÃ³n y comunicaciÃ³n."
                                        },
                                        {
                                            "id": 5,
                                            "nombre": "MÃ³vil",
                                            "categoriaPadre": "ComputaciÃ³n y comunicaciÃ³n."
                                        },
                                        {
                                            "id": 6,
                                            "nombre": "Radio",
                                            "categoriaPadre": "ElectrodomÃ©stico"
                                        },
                                        {
                                            "id": 7,
                                            "nombre": "Aire acondicionado",
                                            "categoriaPadre": "ElectrodomÃ©stico"
                                        }
                                    ] 
                                    """)))
    })
    @GetMapping("")
    public List<GetCategoriaSinListasDto> getAll() {
        return categoriaService.findAll();
    }

    @Operation(summary = "Obtener una categoría por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoriaDto.class),
                            examples = @ExampleObject(value = """ 
                                    {
                                        "id": 1,
                                        "nombre": "ComputaciÃ³n y comunicaciÃ³n.",
                                        "categoriaPadre": "Ninguna",
                                        "listaSubCategorias": [
                                            {
                                                "id": 3,
                                                "nombre": "Ordenador"
                                            },
                                            {
                                                "id": 4,
                                                "nombre": "Tablet"
                                            },
                                            {
                                                "id": 5,
                                                "nombre": "MÃ³vil"
                                            }
                                        ],
                                        "listaIncidencias": [
                                            {
                                                "id": 1,
                                                "fecha": "2025-02-05T10:00:00",
                                                "titulo": "Fallo en la red Wi-Fi",
                                                "descripcion": "No hay conexiÃ³n en la sala 102 debido a un fallo en el router.",
                                                "estado": "PENDIENTE",
                                                "urgencia": true
                                            },
                                            {
                                                "id": 6,
                                                "fecha": "2025-02-05T15:30:00",
                                                "titulo": "Problemas con la impresora",
                                                "descripcion": "La impresora no imprime en el aula 102.",
                                                "estado": "TRABAJANDO",
                                                "urgencia": false
                                            }
                                        ]
                                    }
                                    """))),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public GetCategoriaDto getById(@PathVariable Long id) {
        List<GetCategoriaBasicaDto> listaSubCategorias =
                categoriaService.getSubCategoriasDtoById(id);
        List<GetIncidenciaSinCategoriaDto> listaIncidencias =
                categoriaService.getIncidenciasByCategoriaId(id);
        Categoria categoria = categoriaService.findById(id);

        return GetCategoriaDto.of(categoria, listaSubCategorias, listaIncidencias);
    }

    @Operation(summary = "Crear una nueva categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class),
                            examples = @ExampleObject(value = """ 
                                    {
                                        "nombre": "Ordenadores"
                                    }
                                    """)))
    })
    @PostMapping("")
    public ResponseEntity<Categoria> create(@RequestBody EditCatgeoriaCmd nuevoCategoriaCmd) {
        Categoria nuevaCategoria = categoriaService.save(nuevoCategoriaCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @Operation(summary = "Editar una categoría existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría editada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetCategoriaDto.class),
                            examples = @ExampleObject(value = """ 
                                    {
                                        "nombre":"Robótica"
                                    }
                                    """))),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada",
                    content = @Content)
    })
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
