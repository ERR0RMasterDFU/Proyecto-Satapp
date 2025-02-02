package com.salesianostriana.dam.proyecto_satapp.services;

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

    public Tecnico save(Tecnico tecnico) {
        tecnico.setRole("Tecnico");
        return tecnicoRepository.save(tecnico);
    }

    public Tecnico edit(Tecnico tecnico, Long id) {
        return tecnicoRepository.findById(id)
                .map(old -> {
                    old.setNombre(tecnico.getNombre());
                    old.setUsername(tecnico.getUsername());
                    old.setEmail(tecnico.getEmail());
                    old.setPassword(tecnico.getPassword());
                    return tecnicoRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("Tecnico no encontrado"));
    }

    public void delete(Long id) {
        tecnicoRepository.deleteById(id);
    }
}
