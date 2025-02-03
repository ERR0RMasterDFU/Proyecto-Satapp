package com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;

import java.util.List;

public record GetUsuarioDto(
        String nombre,
        String username,
        String password,
        String email,
        String role,
        List<GetIncidenciaBasicaDto> listaIncidencia
) {
    public static GetUsuarioDto of(Usuario u, List<GetIncidenciaBasicaDto> listaIncidencia) {
        return new GetUsuarioDto(
                u.getNombre(),
                u.getUsername(),
                u.getPassword(),
                u.getEmail(),
                u.getRole(),
                listaIncidencia
        );
    }
}
