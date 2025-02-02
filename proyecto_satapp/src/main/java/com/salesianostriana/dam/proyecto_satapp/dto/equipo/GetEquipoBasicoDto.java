package com.salesianostriana.dam.proyecto_satapp.dto.equipo;

import com.salesianostriana.dam.proyecto_satapp.models.Equipo;

public record GetEquipoBasicoDto(
    String nombre,
    String caracteristicas
) {
        public static GetEquipoBasicoDto of(Equipo e) {
            return new GetEquipoBasicoDto(
                    e.getNombre(),
                    e.getCaracteristicas()
            );
        }
}
