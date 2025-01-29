package com.salesianostriana.dam.proyecto_satapp.dto;

import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;

public record CreateUbicacionDto(
        String nombre
) {
    public Ubicacion toUbicacion() {
        return Ubicacion.builder()
                .nombre(this.nombre)
                .build();
    }
}
