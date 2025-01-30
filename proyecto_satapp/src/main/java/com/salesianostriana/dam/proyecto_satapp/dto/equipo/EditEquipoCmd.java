package com.salesianostriana.dam.proyecto_satapp.dto.equipo;

import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;

public record EditEquipoCmd(
        String nombre,
        String caracteristicas,
        Long ubicacionId
) {
    // CONSTRUCTOR PARA CONSULTAS
    public EditEquipoCmd(String nombre, String caracteristicas, Ubicacion ubicacion) {
        this(nombre, caracteristicas, ubicacion.getId());
    }

}
