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
<<<<<<< HEAD
=======

>>>>>>> 55c57ba672f0173a7fcfdf75ac16103979fc0f1f

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
                            examples = @ExampleObject(value = "[]")))
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
                            examples = @ExampleObject(value = "{}")))
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

<<<<<<< HEAD
    @Operation(summary = "Editar un historial de cursos específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historial de cursos actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetHistoricoCursosDto.class),
                            examples = @ExampleObject(value = "{}")))
    })
=======
>>>>>>> 55c57ba672f0173a7fcfdf75ac16103979fc0f1f
    @PutMapping("/{id}/curso_escolar/{cursoEscolar}")
    public GetHistoricoCursosDto edit(@PathVariable Long id, @PathVariable String cursoEscolar, @RequestBody EditHistoricoCursosCmd editHistoricoCursosCmd) {
        HistoricoCursos historicoCursos = historicoCursosService.edit(editHistoricoCursosCmd, id, cursoEscolar);
        return GetHistoricoCursosDto.of(historicoCursos);
    }

<<<<<<< HEAD
    @Operation(summary = "Eliminar un historial de cursos específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Historial de cursos eliminado correctamente",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{}")))
    })
=======
>>>>>>> 55c57ba672f0173a7fcfdf75ac16103979fc0f1f
    @DeleteMapping("/{id}/curso_escolar/{cursoEscolar}")
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable String cursoEscolar) {
        historicoCursosService.delete(id, cursoEscolar);
        return ResponseEntity.noContent().build();
    }
<<<<<<< HEAD
}
=======

}

>>>>>>> 55c57ba672f0173a7fcfdf75ac16103979fc0f1f
