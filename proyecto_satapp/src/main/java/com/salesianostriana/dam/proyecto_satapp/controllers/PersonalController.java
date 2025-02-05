package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.EditPersonalCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalDto;
import com.salesianostriana.dam.proyecto_satapp.models.Personal;
import com.salesianostriana.dam.proyecto_satapp.services.PersonalService;
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
@RequiredArgsConstructor
@RequestMapping("/personal")
@Tag(name = "Personal", description = "Gestión del personal dentro del sistema")
public class PersonalController {

    private final PersonalService personalService;

    @Operation(summary = "Obtener todos los registros de personal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de personal obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetPersonalBasicoDto.class),
                            examples = @ExampleObject(value = """ 
                                    """)))
    })
    @GetMapping
    public List<GetPersonalBasicoDto> getAll() {
        return personalService.findAll();
    }

    @Operation(summary = "Obtener un registro de personal por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personal encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetPersonalDto.class),
                            examples = @ExampleObject(value = """ 
                                    """))),
            @ApiResponse(responseCode = "404", description = "Personal no encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public GetPersonalDto getById(@PathVariable Long id) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                personalService.getIncidenciasByPersonalId(id);
        Personal personal = personalService.findById(id);
        return GetPersonalDto.of(personal, listaIncidencias);
    }

    @Operation(summary = "Crear un nuevo registro de personal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Personal creado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetPersonalDto.class),
                            examples = @ExampleObject(value = """ 
                                    """)))
    })
    @PostMapping
    public ResponseEntity<Personal> create(@RequestBody EditPersonalCmd editPersonalCmd) {
        Personal nuevoPersonal = personalService.save(editPersonalCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPersonal);
    }

    @Operation(summary = "Editar un registro de personal existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Personal editado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetPersonalDto.class),
                            examples = @ExampleObject(value = """ 
                                    """))),
            @ApiResponse(responseCode = "404", description = "Personal no encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public GetPersonalDto edit(@PathVariable Long id, @RequestBody EditPersonalCmd editPersonalCmd) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                personalService.getIncidenciasByPersonalId(id);
        Personal personal = personalService.edit(editPersonalCmd, id);
        return GetPersonalDto.of(personal, listaIncidencias);
    }

    @Operation(summary = "Eliminar un registro de personal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Personal eliminado correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Personal no encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
