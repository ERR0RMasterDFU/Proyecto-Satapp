package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.EditIncidenciaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalDto;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;
import com.salesianostriana.dam.proyecto_satapp.models.Personal;
import com.salesianostriana.dam.proyecto_satapp.services.IncidenciaService;
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
@RequestMapping("/incidencia")
@RequiredArgsConstructor
@Tag(name = "Incidencias", description = "Gestión de incidencias dentro del sistema")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;


    @Operation(summary = "Obtener todas las incidencias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de incidencias obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetIncidenciaBasicaDto.class),
                            examples = @ExampleObject(value = """ 
                                    
                                    """)))
    })
    @GetMapping
    public List<GetIncidenciaBasicaDto> getAll() {
        return incidenciaService.findAll();
    }

    @Operation(summary = "Obtener incidencia por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidencia encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetIncidenciaDto.class),
                            examples = @ExampleObject(value = """ 
                                    
                                    """))),
            @ApiResponse(responseCode = "404", description = "Incidencia no encontrada",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public GetIncidenciaDto getById(@PathVariable Long id) {
        Incidencia incidencia = incidenciaService.findById(id);
        return GetIncidenciaDto.of(incidencia);
    }

    @Operation(summary = "Obtener todas las incidencias de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de incidencias del usuario obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetIncidenciaSinUsuarioDto.class),
                            examples = @ExampleObject(value = """ 
                                    
                                    """)))
    })
    @GetMapping("/usuario/{id}")
    public List<GetIncidenciaSinUsuarioDto> getAll(@PathVariable Long id) {
        return incidenciaService.findIncidenciasByUsuarioId(id);
    }

    @Operation(summary = "Crear una nueva incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incidencia creada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetIncidenciaSinUsuarioDto.class),
                            examples = @ExampleObject(value = """ 
                                    {
                                        "titulo": "Ordenador estropeado",
                                        "descripcion": "El ordenador ha cogido fuego y ha sido reducido a cenizas",
                                        "estado": "ABIERTA",
                                        "urgencia": true,
                                        "categoriaId": 3,
                                        "equipoId": 1,
                                        "ubicacionId": 2
                                    }
                                    """)))
    })
    @PostMapping
    public ResponseEntity<GetIncidenciaSinUsuarioDto> create(@RequestBody EditIncidenciaCmd nuevaIncidencia) {
        Incidencia incidencia = incidenciaService.save(nuevaIncidencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetIncidenciaSinUsuarioDto.of(incidencia));
    }
}
