package com.salesianostriana.dam.proyecto_satapp.services;

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

    public Personal save(Personal personal) {
        return personalRepository.save(personal);
    }

    public Personal edit(Personal personal, Long id) {
        return personalRepository.findById(id)
                .map(old -> {
                    old.setNombre(personal.getNombre());
                    old.setUsername(personal.getUsername());
                    old.setEmail(personal.getEmail());
                    old.setPassword(personal.getPassword());
                    return personalRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No se encontró personal"));
    }

    public void delete(Long id) {
        personalRepository.deleteById(id);
    }
}
