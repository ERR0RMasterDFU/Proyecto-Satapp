package com.salesianostriana.dam.proyecto_satapp.services;

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

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario edit(Usuario usuario, Long id) {
        return usuarioRepository.findById(id)
                .map(old -> {
                    old.setNombre(usuario.getNombre());
                    old.setUsername(usuario.getUsername());
                    old.setEmail(usuario.getEmail());
                    old.setPassword(usuario.getPassword());
                    old.setRole(usuario.getRole());
                    return usuarioRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No hay usuario con ID: "+ id));
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }


}
