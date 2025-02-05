package com.salesianostriana.dam.proyecto_satapp.controllers;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.EditPersonalCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.EditUsuarioCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.models.Personal;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;
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
    public List<GetPersonalBasicoDto> getAll() {
        return personalService.findAll();
    }

    @GetMapping("/{id}")
    public GetPersonalDto getById(@PathVariable Long id) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                personalService.getIncidenciasByPersonalId(id);
        Personal personal = personalService.findById(id);
        return GetPersonalDto.of(personal, listaIncidencias);
    }

    @PostMapping
    public ResponseEntity<Personal> create(@RequestBody EditPersonalCmd editPersonalCmd) {
        Personal nuevoPersonal = personalService.save(editPersonalCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPersonal);
    }

    @PutMapping("/{id}")
    public GetPersonalDto edit(@PathVariable Long id, @RequestBody EditPersonalCmd editPersonalCmd) {
        List<GetIncidenciaBasicaDto> listaIncidencias =
                personalService.getIncidenciasByPersonalId(id);
        Personal personal = personalService.edit(editPersonalCmd, id);
        return GetPersonalDto.of(personal, listaIncidencias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}