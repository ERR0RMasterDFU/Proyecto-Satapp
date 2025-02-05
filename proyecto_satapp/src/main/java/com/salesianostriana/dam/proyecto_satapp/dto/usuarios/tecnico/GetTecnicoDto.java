package com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Tecnico;

import java.util.List;

public record GetTecnicoDto(
        Long id,
        String nombre,
        String username,
        String password,
        String email,
        String role,
        List<GetIncidenciaBasicaDto> listaIncidencias,
        List<GetIncidenciaBasicaDto> listaIncidenciasTecnico
) {
    public static GetTecnicoDto of(Tecnico t, List<GetIncidenciaBasicaDto> listaIncidencias, List<GetIncidenciaBasicaDto> listaIncidenciasTecnico) {
        return new GetTecnicoDto(
                t.getId(),
                t.getNombre(),
                t.getUsername(),
                t.getPassword(),
                t.getEmail(),
                t.getRole(),
                listaIncidencias,
                listaIncidenciasTecnico
        );
    }
}
