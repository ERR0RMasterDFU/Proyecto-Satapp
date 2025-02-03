package com.salesianostriana.dam.proyecto_satapp.controllers;


import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.EditTecnicoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.GetTecnicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Tecnico;
import com.salesianostriana.dam.proyecto_satapp.services.TecnicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tecnico")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    @GetMapping
    public List<Tecnico> getAll() {
        return tecnicoService.findAll();
    }

    @GetMapping("/{id}")
    public Tecnico getById(@PathVariable Long id) {
        return tecnicoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<GetTecnicoDto> create(@RequestBody EditTecnicoCmd editTecnicoCmd) {
        Tecnico nuevoTecnico = tecnicoService.save(editTecnicoCmd);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GetTecnicoDto.of(nuevoTecnico));
    }

    @PutMapping("/{id}")
    public GetTecnicoDto edit(@PathVariable Long id, @RequestBody EditTecnicoCmd editTecnicoCmd) {
        return tecnicoService.edit(editTecnicoCmd, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}