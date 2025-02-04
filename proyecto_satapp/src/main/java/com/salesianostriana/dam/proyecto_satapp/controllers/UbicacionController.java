package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.EditUbicacionCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;
import com.salesianostriana.dam.proyecto_satapp.services.UbicacionService;
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
@RequestMapping("/ubicacion")
@RequiredArgsConstructor
@Tag(name = "Ubicaciones", description = "Gestión de ubicaciones dentro del sistema")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @Operation(summary = "Obtener todas las ubicaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ubicaciones obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUbicacionSinListasDto.class)))
    })
    @GetMapping("")
    public List<GetUbicacionSinListasDto> getAll() {
        return ubicacionService.findAll();
    }

    @Operation(summary = "Obtener una ubicación por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ubicación encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUbicacionDto.class))),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public GetUbicacionDto getById(@PathVariable Long id) {
        return ubicacionService.findById(id);
    }

    @Operation(summary = "Crear una nueva ubicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ubicación creada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ubicacion.class)))
    })
    @PostMapping("")
    public ResponseEntity<Ubicacion> create(@RequestBody EditUbicacionCmd nuevaUbicacion) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ubicacionService.save(nuevaUbicacion));
    }

    @Operation(summary = "Editar una ubicación existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ubicación editada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUbicacionDto.class))),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public GetUbicacionDto edit(@RequestBody EditUbicacionCmd aEditar, @PathVariable Long id) {
        return ubicacionService.edit(aEditar, id);
    }

    @Operation(summary = "Eliminar una ubicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ubicación eliminada correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Ubicación no encontrada",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ubicacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
