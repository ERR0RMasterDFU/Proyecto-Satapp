package com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal;


import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Personal;
import com.salesianostriana.dam.proyecto_satapp.models.Tipo;

import java.util.List;

public record GetPersonalDto(
        Long id,
        String nombre,
        String username,
        String password,
        String email,
        String role,
        Tipo tipo,
        List<GetIncidenciaBasicaDto> listaIncidencias
) {
    public static GetPersonalDto of(Personal p, List<GetIncidenciaBasicaDto> listaIncidencias) {
        return new GetPersonalDto(
                p.getId(),
                p.getNombre(),
                p.getUsername(),
                p.getPassword(),
                p.getEmail(),
                p.getRole(),
                p.getTipo(),
                listaIncidencias
        );
    }
}
