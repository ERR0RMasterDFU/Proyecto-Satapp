package com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Tecnico;

import java.util.List;

public record GetTecnicoDto(
        String nombre,
        String username,
        String password,
        String email,
        String role,
        List<GetIncidenciaBasicaDto> listaIncidencias,
        List<GetIncidenciaBasicaDto> listaIncidenciasTecnico
) {
    public static GetTecnicoDto of(Tecnico tecnico, List<GetIncidenciaBasicaDto> listaIncidencias, List<GetIncidenciaBasicaDto> listaIncidenciasTecnico) {
        return new GetTecnicoDto(
                tecnico.getNombre(),
                tecnico.getUsername(),
                tecnico.getPassword(),
                tecnico.getEmail(),
                tecnico.getRole(),
                listaIncidencias,
                listaIncidenciasTecnico
        );
    }
}
