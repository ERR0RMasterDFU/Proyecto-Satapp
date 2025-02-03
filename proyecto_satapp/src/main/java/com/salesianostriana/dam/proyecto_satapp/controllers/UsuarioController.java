package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.EditUsuarioCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.GetUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;
import com.salesianostriana.dam.proyecto_satapp.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PostMapping
    public ResponseEntity<GetUsuarioDto> create(@RequestBody EditUsuarioCmd editUsuarioCmd) {
        Usuario nuevoUsuario = usuarioService.save(editUsuarioCmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetUsuarioDto.of(nuevoUsuario));
    }

    @PutMapping("/{id}")
    public GetUsuarioDto edit(@PathVariable Long id, @RequestBody EditUsuarioCmd editUsuarioCmd) {
        return usuarioService.edit(editUsuarioCmd, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
