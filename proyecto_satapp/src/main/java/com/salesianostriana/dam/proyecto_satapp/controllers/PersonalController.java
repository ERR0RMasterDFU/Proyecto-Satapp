package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.models.Personal;
import com.salesianostriana.dam.proyecto_satapp.services.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personal")
public class PersonalController {

    private final PersonalService personalService;

    @GetMapping
    public List<Personal> getAll() {
        return personalService.findAll();
    }

    @GetMapping("/{id}")
    public Personal getById(@PathVariable Long id) {
        return personalService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Personal> create(@RequestBody Personal personal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        personalService.save(personal));
    }

    @PutMapping("/{id}")
    public Personal edit(@PathVariable Long id, @RequestBody Personal personal) {
        return personalService.edit(personal, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}