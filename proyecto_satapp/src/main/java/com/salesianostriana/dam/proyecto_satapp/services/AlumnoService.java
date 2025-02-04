package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.EditAlumnoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoDto;
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

    public Alumno save(EditAlumnoCmd editAlumnoCmd) {
        Alumno alumno = Alumno.builder()
                .nombre(editAlumnoCmd.nombre())
                .username(editAlumnoCmd.username())
                .password(editAlumnoCmd.password())
                .email(editAlumnoCmd.email())
                .role(editAlumnoCmd.role())
                .build();

        return alumnoRepository.save(alumno);
    }

    public GetAlumnoDto edit(EditAlumnoCmd editAlumnoCmd, Long id) {
        Alumno aEditar = alumnoRepository.findById(id)
                .map(old -> {
                    old.setNombre(editAlumnoCmd.nombre());
                    old.setUsername(editAlumnoCmd.username());
                    old.setEmail(editAlumnoCmd.email());
                    old.setPassword(editAlumnoCmd.password());
                    old.setRole(editAlumnoCmd.role());
                    return alumnoRepository.save(old);
                }).orElseThrow(() -> new EntityNotFoundException("No hay alumno con ID: "+ id));

        return GetAlumnoDto.of(aEditar);
    }


    public void delete(Long id) {
        alumnoRepository.deleteById(id);
    }
}
