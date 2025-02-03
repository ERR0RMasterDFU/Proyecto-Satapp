package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.EditPersonalCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.models.Personal;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;
import com.salesianostriana.dam.proyecto_satapp.repositories.IncidenciaRepository;
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
    private final IncidenciaRepository incidenciaRepository;

    public List<GetPersonalBasicoDto> findAll() {
        List<GetPersonalBasicoDto> listaPersonal = personalRepository.findAllBasicoDto();

        if (listaPersonal.isEmpty()) {
            throw new EntityNotFoundException("No existe ningún Personal con esos criterios de búsqueda");
        }
        return listaPersonal;
    }

    public GetPersonalDto findById(Long id) {

        List<GetIncidenciaBasicaDto> listaIncidencias =
                incidenciaRepository.findIncidenciasByPersonalId(id);

        Optional<Personal> personal = personalRepository.findById(id);

        if (personal.isPresent()) {
            return GetPersonalDto.of(personal.get(), listaIncidencias);
        } else {
            throw new EntityNotFoundException("No existe ningún Personal con ID: " + id);
        }
    }

    public Personal save(EditPersonalCmd editPersonalCmd) {
        Personal personal = Personal.builder()
                .nombre(editPersonalCmd.nombre())
                .username(editPersonalCmd.username())
                .password(editPersonalCmd.password())
                .email(editPersonalCmd.email())
                .role(editPersonalCmd.role())
                .tipo(editPersonalCmd.tipo())
                .build();

        return personalRepository.save(personal);
    }

    /*public GetPersonalDto edit(EditPersonalCmd editPersonalCmd, Long id) {
        Personal aEditar = personalRepository.findById(id)
                .map(old -> {
                    old.setNombre(editPersonalCmd.nombre());
                    old.setUsername(editPersonalCmd.username());
                    old.setEmail(editPersonalCmd.email());
                    old.setPassword(editPersonalCmd.password());
                    old.setRole(editPersonalCmd.role());
                    return personalRepository.save(old);
                }).orElseThrow(() -> new EntityNotFoundException("No existe ningún Usuario con ID: " + id));

        return GetPersonalDto.of(aEditar);
    }*/


    public void delete(Long id) {
        personalRepository.deleteById(id);
    }
}
