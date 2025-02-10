package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.EditHistoricoCursosCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.nota.EditNotaCmd;
import com.salesianostriana.dam.proyecto_satapp.models.*;
import com.salesianostriana.dam.proyecto_satapp.repositories.IncidenciaRepository;
import com.salesianostriana.dam.proyecto_satapp.repositories.TecnicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final IncidenciaRepository incidenciaRepository;
    private final TecnicoRepository tecnicoRepository;

    /*
        public List<GetHistoricoCursosBasicoDto> getHistoricoCursosByAlumnoId(Long id) {

            List<GetHistoricoCursosBasicoDto> listaHC = alumnoRepository.findHistoricoCursosByAlumnoId(id);

            if (listaHC.isEmpty()) {
                throw new EntityNotFoundException("No existen Históricos con esos criterios de búsqueda");
            }else{
                return listaHC;
            }
        }
    */
    public Nota save(EditNotaCmd editNotaCmd) {

        Optional<Incidencia> incidencia =
                incidenciaRepository.findById(editNotaCmd.incidenciaId());

        Optional<Tecnico> tecnico =
                tecnicoRepository.findById(editNotaCmd.tecnicoId());

        if (incidencia.isPresent() && tecnico.isPresent()) {
            Nota nota = Nota.builder()
                    .incidencia(incidencia.get())
                    .fecha(LocalDateTime.now())
                    .autor(tecnico.get().getNombre())
                    .contenido(editNotaCmd.contenido())
                    .build();

            incidencia.get().addNota(nota);
            incidenciaRepository.save(incidencia.get());
            return nota;

        } else {
            if(incidencia.isEmpty()) {
                throw new EntityNotFoundException("No existe ninguna Incidencia con ID: " + editNotaCmd.incidenciaId());
            } else {
                throw new EntityNotFoundException("No existe ningún Técnico con ID: " + editNotaCmd.tecnicoId());
            }
        }
    }

    /*
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
*/
}

