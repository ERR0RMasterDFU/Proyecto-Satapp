package com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos;

import com.salesianostriana.dam.proyecto_satapp.models.HistoricoCursos;

public record GetHistoricoCursosBasicoDto(
        String cursoEscolar,
        String curso
){
    public static GetHistoricoCursosBasicoDto of(HistoricoCursos hc) {
        return new GetHistoricoCursosBasicoDto(
                hc.getCursoEscolar(),
                hc.getCurso()
        );
    }
}
