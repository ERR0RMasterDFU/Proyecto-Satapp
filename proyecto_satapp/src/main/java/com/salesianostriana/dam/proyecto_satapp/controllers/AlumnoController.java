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
@RequestMapping("/alumno")
@Tag(name = "Alumnos", description = "Gestión de alumnos dentro del sistema")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Operation(summary = "Obtener todos los alumnos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alumnos obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetAlumnoBasicoDto.class),
                            examples = @ExampleObject(value = """ 
                                    [
                                        {
                                            "id": 4,
                                            "nombre": "Giorno Giovanna",
                                            "username": "GoldExperienceRequiem",
                                            "email": "giorno.giovanna@gmail.com",
                                            "role": "USER"
                                        },
                                        {
                                            "id": 5,
                                            "nombre": "Diego Brando",
                                            "username": "TheWorldAU",
                                            "email": "diego.brando@gmail.com",
                                            "role": "USER"
                                        },
                                        {
                                            "id": 6,
                                            "nombre": "Anasui Narciso",
                                            "username": "DiverDown",
                                            "email": "anasui.narciso@gmail.com",
                                            "role": "USER"
                                        }
                                    ]
                                    """)))
    })
    @GetMapping
    public List<GetAlumnoBasicoDto> getAll() {
        return alumnoService.findAll();
    }

    @Operation(summary = "Obtener un alumno por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetAlumnoDto.class),
                            examples = @ExampleObject(value = """ 
                                    {
                                        "id": 4,
                                        "nombre": "Giorno Giovanna",
                                        "username": "GoldExperienceRequiem",
                                        "password": "MuD4MuD4",
                                        "email": "giorno.giovanna@gmail.com",
                                        "role": "USER",
                                        "listaIncidencias": [
                                            {
                                                "id": 2,
                                                "fecha": "2025-02-05T11:00:00",
                                                "titulo": "Aire acondicionado no funciona",
                                                "descripcion": "El aire acondicionado del gimnasio no responde.",
                                                "estado": "ABIERTA",
                                                "urgencia": false,
                                                "categoria": {
                                                    "id": 7,
                                                    "nombre": "Aire acondicionado"
                                                }
                                            },
                                            {
                                                "id": 8,
                                                "fecha": "2025-02-05T17:00:00",
                                                "titulo": "No carga el dispositivo",
                                                "descripcion": "El dispositivo de tablet Samsung Galaxy S7 no carga en el aula 204.",
                                                "estado": "PENDIENTE",
                                                "urgencia": true,
                                                "categoria": {
                                                    "id": 4,
                                                    "nombre": "Tablet"
                                                }
                                            }
                                        ],
                                        "listaHistoricoCursos": [
                                            {
                                                "curso": "1ÂºDAM",
                                                "cursoEscolar": "2023-2024"
                                            },
                                            {
                                                "curso": "1ÂºElectricidad",
                                                "cursoEscolar": "2024-2025"
                                            }
                                        ]
                                    }
                                    """))),
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
                            schema = @Schema(implementation = Alumno.class),
                            examples = @ExampleObject(value = """ 
                                    {
                                        "nombre":"Johnny Joestar",
                                        "username":"Tusk",
                                        "password":"r0ta310NDoR4dA",
                                        "email":"steelball.run@gmail.com",
                                        "role": "USER"
                                    }
                                    """)))
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
                            schema = @Schema(implementation = GetAlumnoDto.class),
                            examples = @ExampleObject(value = """ 
                                    {
                                        "nombre": "Narancia Ghirga",
                                        "username": "Aerosmith",
                                        "password": "N4ra69o5M1th",
                                        "email": "lil.bomber@gmail.com",
                                        "role": "USER"
                                    }
                                    """))),
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
