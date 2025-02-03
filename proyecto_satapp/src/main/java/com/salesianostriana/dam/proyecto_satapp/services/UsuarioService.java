package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.EditUsuarioCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.GetUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;
import com.salesianostriana.dam.proyecto_satapp.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron usuarios");
        }
        return usuarios;
    }

    /*public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No hay usuario con ID: "+ id));
    }*/

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new EntityNotFoundException("No se encontro el usuario");
        }
    }

    /*public Usuario save(Usuario usuario) {
        return usuarioRepository.save(Usuario.builder()
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .username(usuario.getUsername())
                .role(usuario.getRole())
                .password(usuario.getPassword())
                .build());
    }*/

    public Usuario save(EditUsuarioCmd editUsuarioCmd) {
        Usuario usuario = Usuario.builder()
                .nombre(editUsuarioCmd.nombre())
                .username(editUsuarioCmd.username())
                .password(editUsuarioCmd.password())
                .email(editUsuarioCmd.email())
                .role(editUsuarioCmd.role())
                .build();

        return usuarioRepository.save(usuario);
    }

    public GetUsuarioDto edit(EditUsuarioCmd editUsuarioCmd, Long id) {
        Usuario aEditar = usuarioRepository.findById(id)
                .map(old -> {
                    old.setNombre(editUsuarioCmd.nombre());
                    old.setUsername(editUsuarioCmd.username());
                    old.setEmail(editUsuarioCmd.email());
                    old.setPassword(editUsuarioCmd.password());
                    old.setRole(editUsuarioCmd.role());
                    return usuarioRepository.save(old);
                }).orElseThrow(() -> new EntityNotFoundException("No hay usuario con ID: "+ id));

                return GetUsuarioDto.of(aEditar);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }


}
