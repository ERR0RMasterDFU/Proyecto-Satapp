package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.EditUbicacionCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionConListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionDto;
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
    public List<GetUbicacionDto> getAll() {
        return ubicacionService.findAll();
    }

    @GetMapping("/{id}")
    public GetUbicacionConListasDto getById(@PathVariable Long id) {
        return ubicacionService.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<Ubicacion> create(@RequestBody Ubicacion nuevaUbicacion) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ubicacionService.save(nuevaUbicacion));
    }

    @PutMapping("/{id}")
    public Ubicacion edit(@RequestBody EditUbicacionCmd aEditar, @PathVariable Long id) {
        return ubicacionService.edit(aEditar, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ubicacionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
