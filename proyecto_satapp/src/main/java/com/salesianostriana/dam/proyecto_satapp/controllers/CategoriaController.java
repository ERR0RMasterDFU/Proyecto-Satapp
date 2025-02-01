package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.EditCatgeoriaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.EditEquipoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoConUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.models.Categoria;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;
import com.salesianostriana.dam.proyecto_satapp.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping("")
    public ResponseEntity<Categoria> create(@RequestBody EditCatgeoriaCmd nuevoCategoriaCmd) {
        Categoria nuevaCategoria = categoriaService.save(nuevoCategoriaCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

}
