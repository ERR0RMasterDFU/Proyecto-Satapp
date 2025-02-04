package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.EditAlumnoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.EditUsuarioCmd;
import com.salesianostriana.dam.proyecto_satapp.models.Alumno;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;
import com.salesianostriana.dam.proyecto_satapp.services.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping
    public List<GetAlumnoBasicoDto> getAll() {
        return alumnoService.findAll();
    }

    @GetMapping("/{id}")
    public GetAlumnoDto getById(@PathVariable Long id) {
        return alumnoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Alumno> create(@RequestBody EditAlumnoCmd editAlumnoCmd) {
        Alumno nuevoAlumno = alumnoService.save(editAlumnoCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlumno);
    }
/*
    @PutMapping("/{id}")
    public GetAlumnoDto edit(@PathVariable Long id, @RequestBody EditAlumnoCmd editAlumnoCmd) {
        return alumnoService.edit(editAlumnoCmd, id);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        alumnoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
