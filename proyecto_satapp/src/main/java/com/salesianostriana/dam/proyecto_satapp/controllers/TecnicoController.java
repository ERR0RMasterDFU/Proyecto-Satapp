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
            schema = @Schema(implementation = GetTecnicoDto.class)))
    })
    @GetMapping
    public List<GetTecnicoBasicoDto> getAll() {
        return tecnicoService.findAll();
    }

    @GetMapping("/{id}")
    public GetTecnicoDto getById(@PathVariable Long id) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                tecnicoService.getIncidenciasByTecnicoId(id);
        List<GetIncidenciaBasicaDto> listaIncidenciastecnico =
                tecnicoService.getIncidenciasTecnicoByTecnicoId(id);
        Tecnico tecnico = tecnicoService.findById(id);

        return GetTecnicoDto.of(tecnico, listaIncidencias, listaIncidenciastecnico);
    }

    @PostMapping
    public ResponseEntity<Tecnico> create(@RequestBody EditTecnicoCmd editTecnicoCmd) {
        Tecnico nuevoTecnico = tecnicoService.save(editTecnicoCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTecnico);
    }

    @PutMapping("/{id}")
    public GetTecnicoDto edit(@PathVariable Long id, @RequestBody EditTecnicoCmd editTecnicoCmd) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                tecnicoService.getIncidenciasByTecnicoId(id);
        List<GetIncidenciaBasicaDto> listaIncidenciastecnico =
                tecnicoService.getIncidenciasTecnicoByTecnicoId(id);
        Tecnico tecnico = tecnicoService.edit(editTecnicoCmd, id);

        return GetTecnicoDto.of(tecnico, listaIncidencias, listaIncidenciastecnico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}