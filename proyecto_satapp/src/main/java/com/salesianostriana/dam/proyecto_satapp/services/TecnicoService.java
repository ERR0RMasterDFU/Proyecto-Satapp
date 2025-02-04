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


    public List<GetTecnicoBasicoDto> findAll() {
        List<GetTecnicoBasicoDto> tecnicos = tecnicoRepository.findAllBasicoDto();

        if (tecnicos.isEmpty()) {
            throw new EntityNotFoundException("No existen Técnicos con esos criterios de búsqueda");
        }
        return tecnicos;
    }

    public GetTecnicoDto findById(Long id) {

        List<GetIncidenciaBasicaDto> listaIncidencias =
                incidenciaRepository.findIncidenciasByTecnicoId(id);

        List<GetIncidenciaBasicaDto> listaIncidenciasTecnico =
                incidenciaRepository.findIncidenciasTecnicoByTecnicoId(id);

        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);

        if (tecnico.isPresent()) {
            return GetTecnicoDto.of(tecnico.get(), listaIncidencias, listaIncidenciasTecnico);
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
/*
    public GetTecnicoDto edit(EditTecnicoCmd editTecnicoCmd, Long id) {
        Tecnico aEditar = tecnicoRepository.findById(id)
                .map(old -> {
                    old.setNombre(editTecnicoCmd.nombre());
                    old.setUsername(editTecnicoCmd.username());
                    old.setEmail(editTecnicoCmd.email());
                    old.setPassword(editTecnicoCmd.password());
                    old.setRole(editTecnicoCmd.role());
                    return tecnicoRepository.save(old);
                }).orElseThrow(() -> new EntityNotFoundException("No hay tecnico con ID: "+ id));

        return GetTecnicoDto.of(aEditar);
    }*/


    public void delete(Long id) {
        tecnicoRepository.deleteById(id);
    }
}
