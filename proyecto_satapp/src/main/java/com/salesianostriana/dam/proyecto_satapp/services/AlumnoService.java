package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.EditAlumnoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.EditUsuarioCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.models.Alumno;
import com.salesianostriana.dam.proyecto_satapp.models.Usuario;
import com.salesianostriana.dam.proyecto_satapp.repositories.AlumnoRepository;
import com.salesianostriana.dam.proyecto_satapp.repositories.IncidenciaRepository;
import com.salesianostriana.dam.proyecto_satapp.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final IncidenciaRepository incidenciaRepository;

    public List<GetAlumnoBasicoDto> findAll() {
        List<GetAlumnoBasicoDto> alumnos = alumnoRepository.findAllBasicoDto();

        if (alumnos.isEmpty()) {
            throw new EntityNotFoundException("No existen Alumnos con esos criterios de búsqueda");
        }
        return alumnos;
    }

    public GetAlumnoDto findById(Long id) {

        List<GetIncidenciaBasicaDto> listaIncidencias =
                incidenciaRepository.findIncidenciasByAlumnoId(id);

        List<GetHistoricoCursosBasicoDto> listaHistoricoCursos =
                alumnoRepository.findHistoricoCursosByAlumnoId(id);

        Optional<Alumno> alumno = alumnoRepository.findById(id);
        if (alumno.isPresent()) {
            return GetAlumnoDto.of(alumno.get(), listaIncidencias, listaHistoricoCursos);
        } else {
            throw new EntityNotFoundException("No existe ningún Alumno con ID: " + id);
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

        List<GetIncidenciaBasicaDto> listaIncidencias =
                incidenciaRepository.findIncidenciasByAlumnoId(id);

        List<GetHistoricoCursosBasicoDto> listaHistoricoCursos =
                alumnoRepository.findHistoricoCursosByAlumnoId(id);

        Alumno aEditar = alumnoRepository.findById(id)
                .map(old -> {
                    old.setNombre(editAlumnoCmd.nombre());
                    old.setUsername(editAlumnoCmd.username());
                    old.setEmail(editAlumnoCmd.email());
                    old.setPassword(editAlumnoCmd.password());
                    old.setRole(editAlumnoCmd.role());
                    return alumnoRepository.save(old);
                }).orElseThrow(() -> new EntityNotFoundException("No hay alumno con ID: "+ id));

        return GetAlumnoDto.of(aEditar, listaIncidencias, listaHistoricoCursos);
    }

/*
    public void delete(Long id) {
        alumnoRepository.deleteById(id);
    }*/
}
