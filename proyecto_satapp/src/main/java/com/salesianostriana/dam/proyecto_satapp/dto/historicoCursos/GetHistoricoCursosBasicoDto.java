package com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos;

import com.salesianostriana.dam.proyecto_satapp.models.HistoricoCursos;

public record GetHistoricoCursosBasicoDto(
        String curso,
        String cursoEscolar
){
    public static GetHistoricoCursosBasicoDto of(HistoricoCursos hc) {
        return new GetHistoricoCursosBasicoDto(
                hc.getCurso(),
                hc.getCursoEscolar()
        );
    }
}
