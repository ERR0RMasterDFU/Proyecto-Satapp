package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;
import com.salesianostriana.dam.proyecto_satapp.services.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ubicacion")
@RequiredArgsConstructor
public class UbicacionController {

    private final UbicacionService ubicacionService;

    /*@GetMapping
    public List<Producto> getAll() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public Producto getById(@PathVariable Long id) {
        return productoService.findById(id);
    }*/

    @PostMapping("")
    public ResponseEntity<Ubicacion> create(@RequestBody Ubicacion nuevaUbicacion) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ubicacionService.save(nuevaUbicacion));
    }

    /*@PutMapping("/{id}")
    public Producto edit(@RequestBody Producto aEditar,
                         @PathVariable Long id) {
        return productoService.edit(aEditar, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }*/

}
