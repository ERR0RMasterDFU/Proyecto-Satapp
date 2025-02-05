package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.EditHistoricoCursosCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.EditAlumnoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Alumno;
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

    @Operation(summary = "Crear un nuevo historial de cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Historial de cursos creado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetHistoricoCursosDto.class),
                            examples = @ExampleObject(value = """ 
                                    """)))
    })
    @GetMapping("/{id}")
    public List<GetHistoricoCursosBasicoDto> getAllHistoricoCursosByAlumnoId(@PathVariable Long id) {
        return historicoCursosService.getHistoricoCursosByAlumnoId(id);
    }


    @PostMapping
    public ResponseEntity<GetHistoricoCursosDto> create(@RequestBody EditHistoricoCursosCmd editHistoricoCursosCmd) {
        HistoricoCursos nuevoHistoricoCursos = historicoCursosService.save(editHistoricoCursosCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetHistoricoCursosDto.of(nuevoHistoricoCursos));
    }

    @PutMapping("/{id}/curso_escolar/{cursoEscolar}")
    public GetHistoricoCursosDto edit(@PathVariable Long id, @PathVariable String cursoEscolar, @RequestBody EditHistoricoCursosCmd editHistoricoCursosCmd) {
        HistoricoCursos historicoCursos = historicoCursosService.edit(editHistoricoCursosCmd, id, cursoEscolar);
        return GetHistoricoCursosDto.of(historicoCursos);
    }

    @DeleteMapping("/{id}/curso_escolar/{cursoEscolar}")
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable String cursoEscolar) {
        historicoCursosService.delete(id, cursoEscolar);
        return ResponseEntity.noContent().build();
    }

}

