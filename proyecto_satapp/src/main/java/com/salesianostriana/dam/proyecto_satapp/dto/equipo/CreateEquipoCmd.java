package com.salesianostriana.dam.proyecto_satapp.dto.equipo;

public record CreateEquipoCmd(
        String nombre,
        String caracteristicas,
        Long ubicacionId
) {
}
