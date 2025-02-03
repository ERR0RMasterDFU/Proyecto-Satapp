package com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;

import java.util.List;

public record GetUsuarioDto(
        Long id,
        String nombre,
        String username,
        String password,
        String email,
        String role,
        List<GetIncidenciaBasicaDto> listaIncidencias
) {
    public static GetUsuarioDto of(Usuario u, List<GetIncidenciaBasicaDto> listaIncidencias) {
        return new GetUsuarioDto(
                u.getId(),
                u.getNombre(),
                u.getUsername(),
                u.getPassword(),
                u.getEmail(),
                u.getRole(),
                listaIncidencias
        );
    }
}
