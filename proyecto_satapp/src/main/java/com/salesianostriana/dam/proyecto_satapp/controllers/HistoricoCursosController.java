package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.EditHistoricoCursosCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosDto;
import com.salesianostriana.dam.proyecto_satapp.models.HistoricoCursos;
import com.salesianostriana.dam.proyecto_satapp.services.HistoricoCursosService;
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
@RequestMapping("/historico_cursos")
@Tag(name = "Histórico de Cursos", description = "Gestión del historial de cursos de los alumnos")
public class HistoricoCursosController {

    private final HistoricoCursosService historicoCursosService;

    @Operation(summary = "Obtener el historial de cursos de un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetHistoricoCursosBasicoDto.class),
                            examples = @ExampleObject(value = """
                                    [
                                        {
                                            "curso": "1ÂºDAM",
                                            "cursoEscolar": "2023-2024"
                                        },
                                        {
                                            "curso": "1ÂºElectricidad",
                                            "cursoEscolar": "2024-2025"
                                        }
                                    ]
                                    """)))
    })
    @GetMapping("/{id}")
    public List<GetHistoricoCursosBasicoDto> getAllHistoricoCursosByAlumnoId(@PathVariable Long id) {
        return historicoCursosService.getHistoricoCursosByAlumnoId(id);
    }

    @Operation(summary = "Crear un nuevo historial de cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Historial de cursos creado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetHistoricoCursosDto.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "alumnoId": "6",
                                        "curso": "1º DAM",
                                        "cursoEscolar": "2022-2023"
                                    }
                                    """)))
    })
    @PostMapping
    public ResponseEntity<GetHistoricoCursosDto> create(@RequestBody EditHistoricoCursosCmd editHistoricoCursosCmd) {
        HistoricoCursos nuevoHistoricoCursos = historicoCursosService.save(editHistoricoCursosCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetHistoricoCursosDto.of(nuevoHistoricoCursos));
    }

    @Operation(summary = "Editar un historial de cursos específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historial de cursos actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetHistoricoCursosDto.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "curso": "2º DAM"
                                    }
                                    """)))
    })
    @PutMapping("/{id}/curso_escolar/{cursoEscolar}")
    public GetHistoricoCursosDto edit(@PathVariable Long id, @PathVariable String cursoEscolar, @RequestBody EditHistoricoCursosCmd editHistoricoCursosCmd) {
        HistoricoCursos historicoCursos = historicoCursosService.edit(editHistoricoCursosCmd, id, cursoEscolar);
        return GetHistoricoCursosDto.of(historicoCursos);
    }

    @Operation(summary = "Eliminar un historial de cursos específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Historial de cursos eliminado correctamente",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{}")))
    })
    @DeleteMapping("/{id}/curso_escolar/{cursoEscolar}")
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable String cursoEscolar) {
        historicoCursosService.delete(id, cursoEscolar);
        return ResponseEntity.noContent().build();
    }
}
