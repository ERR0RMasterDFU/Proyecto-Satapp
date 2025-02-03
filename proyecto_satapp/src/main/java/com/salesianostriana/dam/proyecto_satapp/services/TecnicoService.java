package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.EditTecnicoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.GetTecnicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Tecnico;
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

    public List<Tecnico> findAll() {
        List<Tecnico> tecnicos = tecnicoRepository.findAll();

        if (tecnicos.isEmpty()) {
            throw new EntityNotFoundException("Tecnico no encontrado");
        }
        return tecnicos;
    }

    public Tecnico findById(Long id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);

        if (tecnico.isPresent()) {
            return tecnico.get();
        } else {
            throw new EntityNotFoundException("Tecnico no encontrado");
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
    }


    public void delete(Long id) {
        tecnicoRepository.deleteById(id);
    }
}
