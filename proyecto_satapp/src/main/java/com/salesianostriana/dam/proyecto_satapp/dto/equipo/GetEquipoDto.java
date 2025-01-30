package com.salesianostriana.dam.proyecto_satapp.dto.equipo;

import com.salesianostriana.dam.proyecto_satapp.models.Equipo;

public record GetEquipoDto(
    //Long id,
    String nombre,
    String caracteristicas
) {
        public static GetEquipoDto of(Equipo e) {
            return new GetEquipoDto(
                    //e.getId(),
                    e.getNombre(),
                    e.getCaracteristicas()
            );
        }
}
