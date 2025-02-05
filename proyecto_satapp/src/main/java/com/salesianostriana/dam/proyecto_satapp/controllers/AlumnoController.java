package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.EditAlumnoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Alumno;
import com.salesianostriana.dam.proyecto_satapp.services.AlumnoService;
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
@RequestMapping("/alumno")
@Tag(name = "Alumnos", description = "Gestión de alumnos dentro del sistema")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Operation(summary = "Obtener todos los alumnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alumnos obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetAlumnoBasicoDto.class)))
    })
    @GetMapping
    public List<GetAlumnoBasicoDto> getAll() {
        return alumnoService.findAll();
    }

    @Operation(summary = "Obtener un alumno por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetAlumnoDto.class))),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public GetAlumnoDto getById(@PathVariable Long id) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                alumnoService.getIncidenciasByAlumnoId(id);
        List<GetHistoricoCursosBasicoDto> listaHistoricoCursos =
                alumnoService.getHistoricoCursosByAlumnoId(id);
        Alumno alumno = alumnoService.findById(id);

        return GetAlumnoDto.of(alumno, listaIncidencias, listaHistoricoCursos);
    }

    @Operation(summary = "Crear un nuevo alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alumno creado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class)))
    })
    @PostMapping
    public ResponseEntity<Alumno> create(@RequestBody EditAlumnoCmd editAlumnoCmd) {
        Alumno nuevoAlumno = alumnoService.save(editAlumnoCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlumno);
    }

    @Operation(summary = "Editar un alumno existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno editado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetAlumnoDto.class))),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public GetAlumnoDto edit(@PathVariable Long id, @RequestBody EditAlumnoCmd editAlumnoCmd) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                alumnoService.getIncidenciasByAlumnoId(id);
        List<GetHistoricoCursosBasicoDto> listaHistoricoCursos =
                alumnoService.getHistoricoCursosByAlumnoId(id);
        Alumno alumno = alumnoService.edit(editAlumnoCmd, id);

        return GetAlumnoDto.of(alumno, listaIncidencias, listaHistoricoCursos);
    }

    @Operation(summary = "Eliminar un alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Alumno eliminado correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Alumno no encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        alumnoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
