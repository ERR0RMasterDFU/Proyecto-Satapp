package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.EditIncidenciaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;
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

@RestController
@RequestMapping("/incidencia")
@RequiredArgsConstructor
@Tag(name = "Incidencias", description = "Gestión de incidencias dentro del sistema")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    @Operation(summary = "Crear una nueva incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incidencia creada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetIncidenciaSinUsuarioDto.class),
                            examples = @ExampleObject(value = """ 
                                    """)))
    })
    @PostMapping
    public ResponseEntity<GetIncidenciaSinUsuarioDto> create(@RequestBody EditIncidenciaCmd nuevaIncidencia) {
        Incidencia incidencia = incidenciaService.save(nuevaIncidencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetIncidenciaSinUsuarioDto.of(incidencia));
    }
}
