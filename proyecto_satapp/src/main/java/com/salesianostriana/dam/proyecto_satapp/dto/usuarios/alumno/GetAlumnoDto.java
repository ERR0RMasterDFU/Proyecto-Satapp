package com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Alumno;

import java.util.List;

public record GetAlumnoDto(
        Long id,
        String nombre,
        String username,
        String password,
        String email,
        String role,
        List<GetIncidenciaBasicaDto> listaIncidencias,
        List<GetHistoricoCursosBasicoDto> listaHistoricoCursos
) {
    public static GetAlumnoDto of(Alumno alumno, List<GetIncidenciaBasicaDto> listaIncidencias, List<GetHistoricoCursosBasicoDto> listaHistoricoCursos) {
        return new GetAlumnoDto(
                alumno.getId(),
                alumno.getNombre(),
                alumno.getUsername(),
                alumno.getPassword(),
                alumno.getEmail(),
                alumno.getRole(),
                listaIncidencias,
                listaHistoricoCursos
        );
    }
}
