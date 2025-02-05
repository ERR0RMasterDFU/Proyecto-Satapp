package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.EditUsuarioCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;
import com.salesianostriana.dam.proyecto_satapp.services.UsuarioService;
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
@RequestMapping("/usuario")
@Tag(name = "Usuarios", description = "Gestión de usuarios dentro del sistema")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Obtener todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUsuarioBasicoDto.class),
                            examples = @ExampleObject(value = """ 
                                    """)))
    })
    @GetMapping
    public List<GetUsuarioBasicoDto> getAll() {
        return usuarioService.findAll();
    }

    @Operation(summary = "Obtener un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUsuarioDto.class),
                            examples = @ExampleObject(value = """ 
                                    """))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public GetUsuarioDto getById(@PathVariable Long id) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                usuarioService.getIncidenciasByUsuarioId(id);
        Usuario usuario = usuarioService.findById(id);
        return GetUsuarioDto.of(usuario, listaIncidencias);
    }

    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class),
                            examples = @ExampleObject(value = """ 
                                    """)))
    })
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody EditUsuarioCmd editUsuarioCmd) {
        Usuario nuevoUsuario = usuarioService.save(editUsuarioCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @Operation(summary = "Editar un usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario editado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetUsuarioDto.class),
                            examples = @ExampleObject(value = """ 
                                    """))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public GetUsuarioDto edit(@PathVariable Long id, @RequestBody EditUsuarioCmd editUsuarioCmd) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                usuarioService.getIncidenciasByUsuarioId(id);
        Usuario usuario = usuarioService.edit(editUsuarioCmd, id);
        return GetUsuarioDto.of(usuario, listaIncidencias);
    }

    @Operation(summary = "Eliminar un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}