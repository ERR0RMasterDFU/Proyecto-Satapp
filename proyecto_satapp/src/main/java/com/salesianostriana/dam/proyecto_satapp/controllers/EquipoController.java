package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.CreateEquipoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoConUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;
import com.salesianostriana.dam.proyecto_satapp.services.EquipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/equipo")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService equipoService;

    /*@GetMapping("")
    public List<GetUbicacionDto> getAll() {
        return ubicacionService.findAllSinListas();
    }

    @GetMapping("/{id}")
    public Ubicacion getById(@PathVariable Long id) {
        return ubicacionService.findById(id);
    }*/

    /*@PostMapping("")
    public ResponseEntity<Equipo> create(@RequestBody CreateEquipoCmd nuevoEquipo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(equipoService.save(nuevoEquipo));
    }*/

    @PostMapping("")
    public ResponseEntity<GetEquipoConUbicacionDto> create(@RequestBody CreateEquipoCmd nuevoEquipoCmd) {
        Equipo nuevoEquipo = equipoService.save(nuevoEquipoCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetEquipoConUbicacionDto.of(nuevoEquipo));
    }

    /*@PostMapping("")
    public ResponseEntity<GetEquipoDto> create(@RequestBody Equipo nuevoEquipo) {
        Equipo equipo = equipoService.save(nuevoEquipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(GetEquipoDto.of(equipo));
    }*/


/*
    @PutMapping("/{id}")
    public Ubicacion edit(@RequestBody EditUbicacionCmd aEditar, @PathVariable Long id) {
        return ubicacionService.edit(aEditar, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ubicacionService.delete(id);
        return ResponseEntity.noContent().build();
    }*/

}
