package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.EditUbicacionCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;
import com.salesianostriana.dam.proyecto_satapp.services.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubicacion")
@RequiredArgsConstructor
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @GetMapping("")
    public List<GetUbicacionSinListasDto> getAll() {
        return ubicacionService.findAll();
    }

    @GetMapping("/{id}")
    public GetUbicacionDto getById(@PathVariable Long id) {
        List<GetEquipoBasicoDto> listaEquipos =
                ubicacionService.getEquiposByUbicacionId(id);
        List<GetIncidenciaBasicaDto> listaIncidencias =
                ubicacionService.getIncidenciasByUbicacionId(id);
        Ubicacion ubicacion = ubicacionService.findById(id);

        return GetUbicacionDto.of(ubicacion, listaEquipos, listaIncidencias);
    }

    @PostMapping("")
    public ResponseEntity<Ubicacion> create(@RequestBody EditUbicacionCmd nuevaUbicacion) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ubicacionService.save(nuevaUbicacion));
    }

    @PutMapping("/{id}")
    public GetUbicacionDto edit(@RequestBody EditUbicacionCmd aEditar, @PathVariable Long id) {
        List<GetEquipoBasicoDto> listaEquipos =
                ubicacionService.getEquiposByUbicacionId(id);
        List<GetIncidenciaBasicaDto> listaIncidencias =
                ubicacionService.getIncidenciasByUbicacionId(id);
        Ubicacion ubicacion = ubicacionService.edit(aEditar, id);

        return GetUbicacionDto.of(ubicacion, listaEquipos, listaIncidencias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ubicacionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
