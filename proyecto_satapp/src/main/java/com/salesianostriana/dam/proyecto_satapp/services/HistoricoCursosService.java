package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.EditHistoricoCursosCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Alumno;
import com.salesianostriana.dam.proyecto_satapp.models.HistoricoCursos;
import com.salesianostriana.dam.proyecto_satapp.repositories.AlumnoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoricoCursosService {

    private final AlumnoRepository alumnoRepository;


    public List<GetHistoricoCursosBasicoDto> getHistoricoCursosByAlumnoId(Long id) {

        List<GetHistoricoCursosBasicoDto> listaHC = alumnoRepository.findHistoricoCursosByAlumnoId(id);

        if (listaHC.isEmpty()) {
            throw new EntityNotFoundException("No existen Históricos con esos criterios de búsqueda");
        }else{
            return listaHC;
        }
    }

    public HistoricoCursos save(EditHistoricoCursosCmd editHistoricoCursosCmd) {

        Optional<Alumno> alumno =
                alumnoRepository.findById(editHistoricoCursosCmd.alumnoId());

        if (alumno.isPresent()) {
            HistoricoCursos historicoCursos = HistoricoCursos.builder()
                    .alumno(alumno.get())
                    .curso(editHistoricoCursosCmd.curso())
                    .cursoEscolar(editHistoricoCursosCmd.cursoEscolar())
                    .build();

            alumno.get().addHistoricoCursos(historicoCursos);
            alumnoRepository.save(alumno.get());
            return historicoCursos;

        } else {
            throw new EntityNotFoundException("No existe ningún Alumno con ID: " + editHistoricoCursosCmd.alumnoId());
        }
    }

    public HistoricoCursos edit(EditHistoricoCursosCmd editHistoricoCursosCmd, Long id, String cursoEscolar) {

        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe ningún Alumno con ID: " + id));

        HistoricoCursos historicoCursos = alumnoRepository.findHistoricoCursosByAlumnoIdAndCursoEscolar(id, cursoEscolar)
                .orElseThrow(() -> new EntityNotFoundException("No existe un histórico de curso para el alumno con ID: " +
                        id + " y curso escolar: " + cursoEscolar));

        historicoCursos.setCurso(editHistoricoCursosCmd.curso());
        alumnoRepository.save(alumno);

        return historicoCursos;
    }

    public void delete(Long id, String cursoEscolar) {

        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe ningún Alumno con ID: " + id));

        HistoricoCursos historicoCursos = alumnoRepository.findHistoricoCursosByAlumnoIdAndCursoEscolar(id, cursoEscolar)
                .orElseThrow(() -> new EntityNotFoundException("No existe un histórico de curso para el alumno con ID: " +
                        id + " y curso escolar: " + cursoEscolar));

        alumno.removeHistoricoCursos(historicoCursos);
        alumnoRepository.save(alumno);
    }

}

