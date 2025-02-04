package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.EditTecnicoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.GetTecnicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Tecnico;
import com.salesianostriana.dam.proyecto_satapp.services.TecnicoService;
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
@RequiredArgsConstructor
@RequestMapping("/tecnico")
@Tag(name = "Técnicos", description = "Gestión de técnicos en el sistema")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    @Operation(summary = "Obtener todos los técnicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de técnicos obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTecnicoDto.class)))
    })
    @GetMapping
    public List<Tecnico> getAll() {
        return tecnicoService.findAll();
    }

    @Operation(summary = "Obtener un técnico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Técnico encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTecnicoDto.class))),
            @ApiResponse(responseCode = "404", description = "Técnico no encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Tecnico getById(@PathVariable Long id) {
        return tecnicoService.findById(id);
    }

    @Operation(summary = "Crear un nuevo técnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Técnico creado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTecnicoDto.class)))
    })
    @PostMapping
    public ResponseEntity<GetTecnicoDto> create(@RequestBody EditTecnicoCmd editTecnicoCmd) {
        Tecnico nuevoTecnico = tecnicoService.save(editTecnicoCmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetTecnicoDto.of(nuevoTecnico));
    }

    @Operation(summary = "Editar un técnico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Técnico editado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTecnicoDto.class))),
            @ApiResponse(responseCode = "404", description = "Técnico no encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public GetTecnicoDto edit(@PathVariable Long id, @RequestBody EditTecnicoCmd editTecnicoCmd) {
        return tecnicoService.edit(editTecnicoCmd, id);
    }

    @Operation(summary = "Eliminar un técnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Técnico eliminado correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Técnico no encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
