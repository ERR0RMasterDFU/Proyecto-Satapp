package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.GetUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;
import com.salesianostriana.dam.proyecto_satapp.repositories.UbicacionRepository;
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
    private final UbicacionRepository ubicacionRepository;

    @GetMapping("")
    public List<GetUbicacionDto> getAll() {
        return ubicacionService.findAllSinListas();
    }

    @GetMapping("/{id}")
    public Ubicacion getById(@PathVariable Long id) {
        return ubicacionService.findById(id);
    }

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
