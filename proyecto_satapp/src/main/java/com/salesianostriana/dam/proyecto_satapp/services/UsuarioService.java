package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.EditUsuarioCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;
import com.salesianostriana.dam.proyecto_satapp.repositories.IncidenciaRepository;
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
    private final IncidenciaRepository incidenciaRepository;

    public List<GetUsuarioBasicoDto> findAll() {
        List<GetUsuarioBasicoDto> usuarios = usuarioRepository.findAllBasicoDto();

        if (usuarios.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron usuarios");
        }
        return usuarios;
    }

    public GetUsuarioDto findById(Long id) {

        List<GetIncidenciaBasicaDto> listaIncidencias =
                incidenciaRepository.findIncidenciasByUsuarioId(id);

        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return GetUsuarioDto.of(usuario.get(), listaIncidencias);
        } else {
            throw new EntityNotFoundException("No existen Usuarios con esos criterios de búsqueda");
        }
    }

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

        List<GetIncidenciaBasicaDto> listaIncidencias =
                incidenciaRepository.findIncidenciasByUsuarioId(id);

        Usuario aEditar = usuarioRepository.findById(id)
                .map(old -> {
                    old.setNombre(editUsuarioCmd.nombre());
                    old.setUsername(editUsuarioCmd.username());
                    old.setEmail(editUsuarioCmd.email());
                    old.setPassword(editUsuarioCmd.password());
                    old.setRole(editUsuarioCmd.role());
                    return usuarioRepository.save(old);
                }).orElseThrow(() -> new EntityNotFoundException("No hay usuario con ID: "+ id));

        return GetUsuarioDto.of(aEditar, listaIncidencias);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }


}
