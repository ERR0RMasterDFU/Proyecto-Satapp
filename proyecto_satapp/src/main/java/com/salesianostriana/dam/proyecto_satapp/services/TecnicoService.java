package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico.EditTecnicoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico.GetTecnicoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico.GetTecnicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.EditUsuarioCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.models.Tecnico;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;
import com.salesianostriana.dam.proyecto_satapp.repositories.IncidenciaRepository;
import com.salesianostriana.dam.proyecto_satapp.repositories.TecnicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final IncidenciaRepository incidenciaRepository;


    // MÉOTDOS NECESARIOS PARA CONVERSIÓN A DTO EN EL CONTROLADOR ------------------------------------------------------------------------------------------------------------

    public List<GetIncidenciaBasicaDto> getIncidenciasByTecnicoId(Long id) {
        return incidenciaRepository.findIncidenciasByTecnicoId(id);
    }

    public List<GetIncidenciaBasicaDto> getIncidenciasTecnicoByTecnicoId(Long id) {
        return incidenciaRepository.findIncidenciasTecnicoByTecnicoId(id);
    }


    // MÉOTDOS PARA EL CONTROLADOR (CRUD) ------------------------------------------------------------------------------------------------------------------------------------

    public List<GetTecnicoBasicoDto> findAll() {
        List<GetTecnicoBasicoDto> tecnicos = tecnicoRepository.findAllBasicoDto();

        if (tecnicos.isEmpty()) {
            throw new EntityNotFoundException("No existen Técnicos con esos criterios de búsqueda");
        } else {
            return tecnicos;
        }
    }

    public Tecnico findById(Long id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);

        if (tecnico.isPresent()) {
            return tecnico.get();
        } else {
            throw new EntityNotFoundException("No existe ningún Técnico con ID: " + id);
        }
    }

    public Tecnico save(EditTecnicoCmd editTecnicoCmd) {
        Tecnico tecnico = Tecnico.builder()
                .nombre(editTecnicoCmd.nombre())
                .username(editTecnicoCmd.username())
                .password(editTecnicoCmd.password())
                .email(editTecnicoCmd.email())
                .role(editTecnicoCmd.role())
                .build();

        return tecnicoRepository.save(tecnico);
    }

    public Tecnico edit(EditTecnicoCmd editTecnicoCmd, Long id) {
        return tecnicoRepository.findById(id)
                .map(old -> {
                    old.setNombre(editTecnicoCmd.nombre());
                    old.setUsername(editTecnicoCmd.username());
                    old.setEmail(editTecnicoCmd.email());
                    old.setPassword(editTecnicoCmd.password());
                    old.setRole(editTecnicoCmd.role());
                    return tecnicoRepository.save(old);
                }).orElseThrow(() -> new EntityNotFoundException("No existe ningún Técnico con ID: " + id));
    }

    public void delete(Long id) {
        tecnicoRepository.deleteById(id);
    }
}
