package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.EditHistoricoCursosCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.EditAlumnoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Alumno;
import com.salesianostriana.dam.proyecto_satapp.models.HistoricoCursos;
import com.salesianostriana.dam.proyecto_satapp.services.HistoricoCursosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/historico_cursos")
public class HistoricoCursosController {

    private final HistoricoCursosService historicoCursosService;

    @PostMapping
    public ResponseEntity<GetHistoricoCursosDto> create(@RequestBody EditHistoricoCursosCmd editHistoricoCursosCmd) {
        HistoricoCursos nuevoHistoricoCursos = historicoCursosService.save(editHistoricoCursosCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetHistoricoCursosDto.of(nuevoHistoricoCursos));
    }

    @PutMapping("/{id}/curso_escolar/{cursoEscolar}")
    public GetHistoricoCursosDto edit(@PathVariable Long id, @PathVariable String cursoEscolar, @RequestBody EditHistoricoCursosCmd editHistoricoCursosCmd) {
        HistoricoCursos historicoCursos = historicoCursosService.edit(editHistoricoCursosCmd, id, cursoEscolar);
        return GetHistoricoCursosDto.of(historicoCursos);
    }

}
