package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.EditIncidenciaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.EditUsuarioCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;
import com.salesianostriana.dam.proyecto_satapp.models.Personal;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;
import com.salesianostriana.dam.proyecto_satapp.services.IncidenciaService;
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
@RequestMapping("/incidencia")
@RequiredArgsConstructor
@Tag(name = "Incidencias", description = "Gestión de incidencias dentro del sistema")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;


    @Operation(summary = "Obtener todas las incidencias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de incidencias obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetIncidenciaBasicaDto.class),
                            examples = @ExampleObject(value = """ 
                                    [
                                        {
                                            "id": 1,
                                            "fecha": "2025-02-05T10:00:00",
                                            "titulo": "Fallo en la red Wi-Fi",
                                            "descripcion": "No hay conexiÃ³n en la sala 102 debido a un fallo en el router.",
                                            "estado": "PENDIENTE",
                                            "urgencia": true,
                                            "categoria": {
                                                "id": 1,
                                                "nombre": "ComputaciÃ³n y comunicaciÃ³n."
                                            }
                                        },
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
                                        }
                                    ]
                                    """)))
    })
    @GetMapping
    public List<GetIncidenciaBasicaDto> getAll() {
        return incidenciaService.findAll();
    }

    @Operation(summary = "Obtener incidencia por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidencia encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetIncidenciaDto.class),
                            examples = @ExampleObject(value = """ 
                                    {
                                        "id": 1,
                                        "fecha": "2025-02-05T10:00:00",
                                        "titulo": "Fallo en la red Wi-Fi",
                                        "descripcion": "No hay conexiÃ³n en la sala 102 debido a un fallo en el router.",
                                        "estado": "PENDIENTE",
                                        "urgencia": true,
                                        "categoria": {
                                            "id": 1,
                                            "nombre": "ComputaciÃ³n y comunicaciÃ³n."
                                        },
                                        "usuario": {
                                            "id": 2,
                                            "nombre": "Will A. Zeppeli",
                                            "username": "HamonMaster",
                                            "email": "will.zeppeli@gmail.com",
                                            "role": "USER"
                                        },
                                        "equipo": {
                                            "id": 3,
                                            "nombre": "MÃ³vil Samsung Galaxy S21",
                                            "caracteristicas": "Pantalla OLED 6.5\\", baterÃ­a 5000mAh"
                                        },
                                        "ubicacion": null
                                    }
                                    """))),
            @ApiResponse(responseCode = "404", description = "Incidencia no encontrada",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public GetIncidenciaDto getById(@PathVariable Long id) {
        Incidencia incidencia = incidenciaService.findById(id);
        return GetIncidenciaDto.of(incidencia);
    }

    @Operation(summary = "Obtener todas las incidencias de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de incidencias del usuario obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetIncidenciaSinUsuarioDto.class),
                            examples = @ExampleObject(value = """ 
                                    [
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
                                            },
                                            "equipo": {
                                                "id": 10,
                                                "nombre": "Aire Acondicionado Daikin Inverter",
                                                "caracteristicas": "TecnologÃ­a inverter, refrigerante ecolÃ³gico"
                                            },
                                            "ubicacion": {
                                                "id": 4,
                                                "nombre": "Gimnasio"
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
                                            },
                                            "equipo": {
                                                "id": 7,
                                                "nombre": "Tablet Samsung Galaxy Tab S7",
                                                "caracteristicas": "Compatible con stylus, baterÃ­a de larga duraciÃ³n"
                                            },
                                            "ubicacion": {
                                                "id": 3,
                                                "nombre": "Aula 204"
                                            }
                                        }
                                    ]
                                    """)))
    })
    @GetMapping("/usuario/{id}")
    public List<GetIncidenciaSinUsuarioDto> getAll(@PathVariable Long id) {
        return incidenciaService.findIncidenciasByUsuarioId(id);
    }

    @Operation(summary = "Crear una nueva incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incidencia creada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetIncidenciaSinUsuarioDto.class),
                            examples = @ExampleObject(value = """ 
                                    {
                                        "titulo": "Ordenador estropeado",
                                        "descripcion": "El ordenador ha cogido fuego y ha sido reducido a cenizas",
                                        "estado": "ABIERTA",
                                        "urgencia": true,
                                        "categoriaId": 3,
                                        "usuarioId": 5,
                                        "equipoId": 1,
                                        "ubicacionId": 2
                                    }
                                    """)))
    })
    @PostMapping
    public ResponseEntity<GetIncidenciaDto> create(@RequestBody EditIncidenciaCmd nuevaIncidencia) {
        Incidencia incidencia = incidenciaService.save(nuevaIncidencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetIncidenciaDto.of(incidencia));
    }

    @Operation(summary = "Editar una incidencia existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidencia editada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetIncidenciaDto.class),
                            examples = @ExampleObject(value = """ 
                                    {
                                        "titulo": "Tablet sin pantalla",
                                        "descripcion": "La tablet se ha quedado ciega.",
                                        "estado": "PENDIENTE",
                                        "urgencia": false,
                                        "categoriaId": 4,
                                        "equipoId": 7,
                                        "ubicacionId": 3
                                    }
                                    """))),
            @ApiResponse(responseCode = "404", description = "Incidencia no encontrada",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public GetIncidenciaDto edit(@PathVariable Long id, @RequestBody EditIncidenciaCmd editIncidenciaCmd) {
        Incidencia incidencia = incidenciaService.edit(editIncidenciaCmd, id);
        return GetIncidenciaDto.of(incidencia);
    }

    @Operation(summary = "Eliminar una incidencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Incidencia eliminada correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Incidencia no encontrada",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        incidenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
