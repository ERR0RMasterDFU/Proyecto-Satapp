package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.EditPersonalCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.EditUsuarioCmd;
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


    // MÉOTDOS NECESARIOS PARA CONVERSIÓN A DTO EN EL CONTROLADOR ------------------------------------------------------------------------------------------------------------

    public List<GetIncidenciaBasicaDto> getIncidenciasByPersonalId(Long id) {
        return incidenciaRepository.findIncidenciasByPersonalId(id);
    }


    // MÉOTDOS PARA EL CONTROLADOR (CRUD) ------------------------------------------------------------------------------------------------------------------------------------

    public List<GetPersonalBasicoDto> findAll() {
        List<GetPersonalBasicoDto> listaPersonal = personalRepository.findAllBasicoDto();

        if (listaPersonal.isEmpty()) {
            throw new EntityNotFoundException("No existe ningún Personal con esos criterios de búsqueda");
        } else {
            return listaPersonal;
        }
    }

    public Personal findById(Long id) {
        Optional<Personal> personal = personalRepository.findById(id);

        if (personal.isPresent()) {
            return personal.get();
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

    public Personal edit(EditPersonalCmd editPersonalCmd, Long id) {
        return personalRepository.findById(id)
                .map(old -> {
                    old.setNombre(editPersonalCmd.nombre());
                    old.setUsername(editPersonalCmd.username());
                    old.setEmail(editPersonalCmd.email());
                    old.setPassword(editPersonalCmd.password());
                    old.setRole(editPersonalCmd.role());
                    old.setTipo(editPersonalCmd.tipo());
                    return personalRepository.save(old);
                }).orElseThrow(() -> new EntityNotFoundException("No existe ningún Usuario con ID: " + id));
    }

    public void delete(Long id) {
        personalRepository.deleteById(id);
    }
}
