package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.models.Alumno;
import com.salesianostriana.dam.proyecto_satapp.repositories.AlumnoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public List<Alumno> findAll() {
        List<Alumno> alumnos = alumnoRepository.findAll();

        if (alumnos.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron alumnos");
        }
        return alumnos;
    }

    public Alumno findById(Long id) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);

        if (alumno.isPresent()) {
            return alumno.get();
        } else {
            throw new EntityNotFoundException("No se encontraron alumnos");
        }
    }

    public Alumno save(Alumno alumno) {
        alumno.setRole("Alumno");
        return alumnoRepository.save(alumno);
    }

    public Alumno edit(Alumno alumno, Long id) {
        return alumnoRepository.findById(id)
                .map(old -> {
                    old.setNombre(alumno.getNombre());
                    old.setUsername(alumno.getUsername());
                    old.setEmail(alumno.getEmail());
                    old.setPassword(alumno.getPassword());
                    return alumnoRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No se encontraron alumnos"));
    }

    public void delete(Long id) {
        alumnoRepository.deleteById(id);
    }
}
