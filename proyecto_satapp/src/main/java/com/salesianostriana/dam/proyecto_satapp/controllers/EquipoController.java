package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.EditEquipoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;
import com.salesianostriana.dam.proyecto_satapp.services.EquipoService;
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
@RequestMapping("/equipo")
@RequiredArgsConstructor
@Tag(name = "Equipo", description = "Controlador para gestionar los equipos")
public class EquipoController {

    private final EquipoService equipoService;

    @Operation(summary = "Obtener todos los equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de equipos obtenida con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetEquipoSinListasDto.class),
                            examples = @ExampleObject(value = """ 
                                    [
                                        {
                                            "id": 9,
                                            "nombre": "Radio Philips Retro 2000",
                                            "caracteristicas": "Reproductor de CD y USB",
                                            "ubicacion": {
                                                "id": 1,
                                                "nombre": "Sala de profesores"
                                            }
                                        },
                                        {
                                            "id": 5,
                                            "nombre": "Aire Acondicionado LG DualCool",
                                            "caracteristicas": "Capacidad 12000 BTU, ahorro energÃ©tico",
                                            "ubicacion": {
                                                "id": 1,
                                                "nombre": "Sala de profesores"
                                            }
                                        },
                                        {
                                            "id": 1,
                                            "nombre": "Ordenador Dell XPS",
                                            "caracteristicas": "Procesador Intel i7, 16GB RAM, SSD 512GB",
                                            "ubicacion": {
                                                "id": 1,
                                                "nombre": "Sala de profesores"
                                            }
                                        },
                                        {
                                            "id": 10,
                                            "nombre": "Aire Acondicionado Daikin Inverter",
                                            "caracteristicas": "TecnologÃ­a inverter, refrigerante ecolÃ³gico",
                                            "ubicacion": {
                                                "id": 2,
                                                "nombre": "Aula 102"
                                            }
                                        },
                                        {
                                            "id": 6,
                                            "nombre": "Ordenador HP Pavilion",
                                            "caracteristicas": "Ryzen 5, 8GB RAM, HDD 1TB",
                                            "ubicacion": {
                                                "id": 2,
                                                "nombre": "Aula 102"
                                            }
                                        },
                                        {
                                            "id": 2,
                                            "nombre": "Tablet iPad Pro",
                                            "caracteristicas": "Pantalla 10\\", 128GB almacenamiento",
                                            "ubicacion": {
                                                "id": 2,
                                                "nombre": "Aula 102"
                                            }
                                        },
                                        {
                                            "id": 7,
                                            "nombre": "Tablet Samsung Galaxy Tab S7",
                                            "caracteristicas": "Compatible con stylus, baterÃ­a de larga duraciÃ³n",
                                            "ubicacion": {
                                                "id": 3,
                                                "nombre": "Aula 204"
                                            }
                                        },
                                        {
                                            "id": 3,
                                            "nombre": "MÃ³vil Samsung Galaxy S21",
                                            "caracteristicas": "Pantalla OLED 6.5\\", baterÃ­a 5000mAh",
                                            "ubicacion": {
                                                "id": 3,
                                                "nombre": "Aula 204"
                                            }
                                        },
                                        {
                                            "id": 8,
                                            "nombre": "MÃ³vil iPhone 13 Pro",
                                            "caracteristicas": "CÃ¡mara 108MP, carga rÃ¡pida 65W",
                                            "ubicacion": {
                                                "id": 4,
                                                "nombre": "Gimnasio"
                                            }
                                        },
                                        {
                                            "id": 4,
                                            "nombre": "Radio Sony ZR-100",
                                            "caracteristicas": "SintonizaciÃ³n AM/FM, altavoces estÃ©reo",
                                            "ubicacion": {
                                                "id": 4,
                                                "nombre": "Gimnasio"
                                            }
                                        }
                                    ]
                                    """)))
    })
    @GetMapping("")
    public List<GetEquipoSinListasDto> getAll() {
        return equipoService.findAll();
    }

    @Operation(summary = "Obtener un equipo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetEquipoDto.class),
                            examples = @ExampleObject(value = """ 
                                    {
                                        "id": 1,
                                        "nombre": "Ordenador Dell XPS",
                                        "caracteristicas": "Procesador Intel i7, 16GB RAM, SSD 512GB",
                                        "ubicacion": {
                                            "id": 1,
                                            "nombre": "Sala de profesores"
                                        },
                                        "listaIncidencias": []
                                    }
                                    """))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    @GetMapping("/{id}")
    public GetEquipoDto getById(@PathVariable Long id) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                equipoService.getIncidenciasByEquipoId(id);
        Equipo equipo = equipoService.findById(id);

        return GetEquipoDto.of(equipo, listaIncidencias);
    }

    @Operation(summary = "Crear un nuevo equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipo creado con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetEquipoSinListasDto.class),
                            examples = @ExampleObject(value = """ 
                                    {
                                        "nombre":"Ordenador viejo",
                                        "caracteristicas": "Capacidad de 16 GB",
                                        "ubicacionId": 2
                                    }
                                    """)))
    })
    @PostMapping("")
    public ResponseEntity<GetEquipoSinListasDto> create(@RequestBody EditEquipoCmd nuevoEquipoCmd) {
        Equipo nuevoEquipo = equipoService.save(nuevoEquipoCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetEquipoSinListasDto.of(nuevoEquipo));
    }

    @Operation(summary = "Editar un equipo existente")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Equipo editado con éxito",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetEquipoDto.class),
                    examples = @ExampleObject(value = """ 
                            {
                                "nombre":"Ordenador roto",
                                "caracteristicas": "Capacidad de 32 GB",
                                "ubicacionId": 4
                            }
                                    """))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    @PutMapping("/{id}")
    public GetEquipoDto edit(@RequestBody EditEquipoCmd aEditar, @PathVariable Long id) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                equipoService.getIncidenciasByEquipoId(id);
        Equipo equipo = equipoService.edit(aEditar, id);

        return GetEquipoDto.of(equipo, listaIncidencias);
    }

    @Operation(summary = "Eliminar un equipo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Equipo eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        equipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
