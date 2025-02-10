package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.EditHistoricoCursosCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosDto;
import com.salesianostriana.dam.proyecto_satapp.dto.nota.EditNotaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.nota.GetNotaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.nota.GetNotaDto;
import com.salesianostriana.dam.proyecto_satapp.models.HistoricoCursos;
import com.salesianostriana.dam.proyecto_satapp.models.Nota;
import com.salesianostriana.dam.proyecto_satapp.services.IncidenciaService;
import com.salesianostriana.dam.proyecto_satapp.services.NotaService;
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
@RequestMapping("/nota")
@Tag(name = "Nota", description = "Gestión de las notas")
public class NotaController {

    private final NotaService notaService;


    @Operation(summary = "Obtener las notas asociadas a una incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de notas obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetNotaDto.class),
                            examples = @ExampleObject(value = """
                                    
                                    """)))
    })
    @GetMapping("/{id}")
    public List<GetNotaBasicaDto> getAllNotasByIncidenciaId(@PathVariable Long id) {
        return notaService.getNotasByIncidenciaId(id);
    }

    @Operation(summary = "Crear un nueva nota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nota creada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetNotaDto.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "incidenciaId": 1,
                                        "tecnicoId": 11,
                                        "contenido": "Su ciclo de vida ha llegado a su fin. Simplemente, no pudimos hacer nada..."
                                    }
                                    """)))
    })
    @PostMapping("")
    public ResponseEntity<GetNotaDto> create(@RequestBody EditNotaCmd editNotaCmd) {
        Nota nuevaNota = notaService.save(editNotaCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetNotaDto.of(nuevaNota));
    }

    @Operation(summary = "Elimina una nota específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nota eliminada correctamente",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{}")))
    })
    @DeleteMapping("/{idIncidencia}/fecha/{fecha}/autor/{idAutor}")
    public ResponseEntity<?> delete(@PathVariable Long idIncidencia, @PathVariable String fecha,
                                    @PathVariable Long idAutor) {
        notaService.delete(idIncidencia, fecha, idAutor);
        return ResponseEntity.noContent().build();
    }

}
