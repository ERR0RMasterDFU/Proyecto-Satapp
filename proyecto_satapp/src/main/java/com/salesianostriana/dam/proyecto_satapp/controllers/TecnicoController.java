package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico.EditTecnicoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico.GetTecnicoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico.GetTecnicoDto;
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
@Tag(name = "Técnico", description = "Gestión de técnicos")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    @Operation(summary = "Obtener todos los técnicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de técnicos obtenida correctamente.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTecnicoBasicoDto.class)))
    })
    @GetMapping
    public List<GetTecnicoBasicoDto> getAll() {
        return tecnicoService.findAll();
    }

    @Operation(summary = "Obtener un técnico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Técnico encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTecnicoDto.class))),
            @ApiResponse(responseCode = "404", description = "Técnico no encontrado.",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public GetTecnicoDto getById(@PathVariable Long id) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                tecnicoService.getIncidenciasByTecnicoId(id);
        List<GetIncidenciaBasicaDto> listaIncidenciastecnico =
                tecnicoService.getIncidenciasTecnicoByTecnicoId(id);
        Tecnico tecnico = tecnicoService.findById(id);

        return GetTecnicoDto.of(tecnico, listaIncidencias, listaIncidenciastecnico);
    }

    @Operation(summary = "Crear un nuevo técnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Técnico creado correctamente.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tecnico.class)))
    })
    @PostMapping
    public ResponseEntity<Tecnico> create(@RequestBody EditTecnicoCmd editTecnicoCmd) {
        Tecnico nuevoTecnico = tecnicoService.save(editTecnicoCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTecnico);
    }

    @Operation(summary = "Editar un técnico existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Técnico editado correctamente.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTecnicoDto.class))),
            @ApiResponse(responseCode = "404", description = "Técnico no encontrado.",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public GetTecnicoDto edit(@PathVariable Long id, @RequestBody EditTecnicoCmd editTecnicoCmd) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                tecnicoService.getIncidenciasByTecnicoId(id);
        List<GetIncidenciaBasicaDto> listaIncidenciastecnico =
                tecnicoService.getIncidenciasTecnicoByTecnicoId(id);
        Tecnico tecnico = tecnicoService.edit(editTecnicoCmd, id);

        return GetTecnicoDto.of(tecnico, listaIncidencias, listaIncidenciastecnico);
    }

    @Operation(summary = "Eliminar un técnico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Técnico eliminado correctamente.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Técnico no encontrado.",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
