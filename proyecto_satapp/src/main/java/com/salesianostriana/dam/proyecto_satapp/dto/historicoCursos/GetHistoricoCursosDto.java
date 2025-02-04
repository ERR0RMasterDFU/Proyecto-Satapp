package com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos;

import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.HistoricoCursos;

public record GetHistoricoCursosDto (
        GetAlumnoBasicoDto alumno,
        String curso,
        String cursoEscolar
){
    public static GetHistoricoCursosDto of(HistoricoCursos hc) {
        return new GetHistoricoCursosDto(
                GetAlumnoBasicoDto.of(hc.getAlumno()),
                hc.getCurso(),
                hc.getCursoEscolar()
        );
    }
}