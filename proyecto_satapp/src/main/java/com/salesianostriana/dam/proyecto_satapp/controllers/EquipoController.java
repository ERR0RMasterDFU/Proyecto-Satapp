package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.EditEquipoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;
import com.salesianostriana.dam.proyecto_satapp.services.EquipoService;
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
@RequestMapping("/equipo")
@RequiredArgsConstructor
@Tag(name = "Equipo", description = "Controlador para gestionar los equipos")
public class EquipoController {

    private final EquipoService equipoService;

    @Operation(summary = "Obtener todos los equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de equipos obtenida con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetEquipoSinListasDto.class)))
    })
    @GetMapping("")
    public List<GetEquipoSinListasDto> getAll() {
        return equipoService.findAll();
    }

    @Operation(summary = "Obtener un equipo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetEquipoDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    @GetMapping("/{id}")
    public GetEquipoDto getById(@PathVariable Long id) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                equipoService.getIncidenciasByEquipoId(id);
        Equipo equipo = equipoService.findById(id);

        return GetEquipoDto.of(equipo, listaIncidencias);
    }

    @Operation(summary = "Crear un nuevo equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipo creado con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetEquipoSinListasDto.class)))
    })
    @PostMapping("")
    public ResponseEntity<GetEquipoSinListasDto> create(@RequestBody EditEquipoCmd nuevoEquipoCmd) {
        Equipo nuevoEquipo = equipoService.save(nuevoEquipoCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetEquipoSinListasDto.of(nuevoEquipo));
    }

    @Operation(summary = "Editar un equipo existente")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Equipo editado con éxito",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetEquipoDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    @PutMapping("/{id}")
    public GetEquipoDto edit(@RequestBody EditEquipoCmd aEditar, @PathVariable Long id) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                equipoService.getIncidenciasByEquipoId(id);
        Equipo equipo = equipoService.edit(aEditar, id);

        return GetEquipoDto.of(equipo, listaIncidencias);
    }

    @Operation(summary = "Eliminar un equipo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Equipo eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        equipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
