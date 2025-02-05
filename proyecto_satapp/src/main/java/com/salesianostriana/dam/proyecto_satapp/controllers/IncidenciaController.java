package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.EditIncidenciaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;
import com.salesianostriana.dam.proyecto_satapp.services.IncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/incidencia")
@RequiredArgsConstructor
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    @PostMapping("")
    public ResponseEntity<GetIncidenciaSinUsuarioDto> create(@RequestBody EditIncidenciaCmd nuevaIncidencia) {
        Incidencia incidencia = incidenciaService.save(nuevaIncidencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetIncidenciaSinUsuarioDto.of(incidencia));
    }

}
