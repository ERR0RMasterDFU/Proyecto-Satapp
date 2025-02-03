package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.EditEquipoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;
import com.salesianostriana.dam.proyecto_satapp.services.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/equipo")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping("")
    public List<GetEquipoSinListasDto> getAll() {
        return equipoService.findAll();
    }

    @GetMapping("/{id}")
    public GetEquipoDto getById(@PathVariable Long id) {
        return equipoService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<GetEquipoSinListasDto> create(@RequestBody EditEquipoCmd nuevoEquipoCmd) {
        Equipo nuevoEquipo = equipoService.save(nuevoEquipoCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetEquipoSinListasDto.of(nuevoEquipo));
    }

    @PutMapping("/{id}")
    public GetEquipoDto edit(@RequestBody EditEquipoCmd aEditar, @PathVariable Long id) {
        Equipo equipoEditado = equipoService.edit(aEditar, id);
        return GetEquipoDto.of(equipoEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        equipoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
