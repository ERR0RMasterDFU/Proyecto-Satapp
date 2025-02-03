package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.EditPersonalCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.GetPersonalDto;
import com.salesianostriana.dam.proyecto_satapp.models.Personal;
import com.salesianostriana.dam.proyecto_satapp.repositories.PersonalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalService {

    private final PersonalRepository personalRepository;

    public List<Personal> findAll() {
        List<Personal> personals = personalRepository.findAll();

        if (personals.isEmpty()) {
            throw new EntityNotFoundException("No se encontró personal");
        }
        return personals;
    }

    public Personal findById(Long id) {
        Optional<Personal> personal = personalRepository.findById(id);

        if (personal.isPresent()) {
            return personal.get();
        } else {
            throw new EntityNotFoundException("No se encontró personal");
        }
    }

    public Personal save(EditPersonalCmd editPersonalCmd) {
        Personal personal = Personal.builder()
                .nombre(editPersonalCmd.nombre())
                .username(editPersonalCmd.username())
                .password(editPersonalCmd.password())
                .email(editPersonalCmd.email())
                .role(editPersonalCmd.role())
                .build();

        return personalRepository.save(personal);
    }

    public GetPersonalDto edit(EditPersonalCmd editPersonalCmd, Long id) {
        Personal aEditar = personalRepository.findById(id)
                .map(old -> {
                    old.setNombre(editPersonalCmd.nombre());
                    old.setUsername(editPersonalCmd.username());
                    old.setEmail(editPersonalCmd.email());
                    old.setPassword(editPersonalCmd.password());
                    old.setRole(editPersonalCmd.role());
                    return personalRepository.save(old);
                }).orElseThrow(() -> new EntityNotFoundException("No hay personal con ID: "+ id));

        return GetPersonalDto.of(aEditar);
    }


    public void delete(Long id) {
        personalRepository.deleteById(id);
    }
}
