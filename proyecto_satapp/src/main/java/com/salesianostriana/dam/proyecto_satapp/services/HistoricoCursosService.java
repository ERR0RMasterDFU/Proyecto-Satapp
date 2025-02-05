package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.EditHistoricoCursosCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico.EditTecnicoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico.GetTecnicoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico.GetTecnicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Alumno;
import com.salesianostriana.dam.proyecto_satapp.models.HistoricoCursos;
import com.salesianostriana.dam.proyecto_satapp.models.Tecnico;
import com.salesianostriana.dam.proyecto_satapp.repositories.AlumnoRepository;
import com.salesianostriana.dam.proyecto_satapp.repositories.IncidenciaRepository;
import com.salesianostriana.dam.proyecto_satapp.repositories.TecnicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoricoCursosService {

    private final AlumnoRepository alumnoRepository;

/*
    public List<GetTecnicoBasicoDto> findAll() {
        List<GetTecnicoBasicoDto> tecnicos = tecnicoRepository.findAllBasicoDto();

        if (tecnicos.isEmpty()) {
            throw new EntityNotFoundException("No existen Técnicos con esos criterios de búsqueda");
        }
        return tecnicos;
    }

    public GetTecnicoDto findById(Long id) {

        List<GetIncidenciaBasicaDto> listaIncidencias =
                incidenciaRepository.findIncidenciasByTecnicoId(id);

        List<GetIncidenciaBasicaDto> listaIncidenciasTecnico =
                incidenciaRepository.findIncidenciasTecnicoByTecnicoId(id);

        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);

        if (tecnico.isPresent()) {
            return GetTecnicoDto.of(tecnico.get(), listaIncidencias, listaIncidenciasTecnico);
        } else {
            throw new EntityNotFoundException("No existe ningún Técnico con ID: " + id);
        }
    }*/

    public HistoricoCursos save(EditHistoricoCursosCmd editHistoricoCursosCmd) {

        Optional<Alumno> nuevoAlumno =
                alumnoRepository.findById(editHistoricoCursosCmd.alumnoId());

        if (nuevoAlumno.isPresent()) {
            HistoricoCursos historicoCursos = HistoricoCursos.builder()
                    .alumno(nuevoAlumno.get())
                    .curso(editHistoricoCursosCmd.curso())
                    .cursoEscolar(editHistoricoCursosCmd.cursoEscolar())
                    .build();

            nuevoAlumno.get().addHistoricoCursos(historicoCursos);
            alumnoRepository.save(nuevoAlumno.get());
            return historicoCursos;

        } else {
            throw new EntityNotFoundException("No existe ningún Alumno con ID: " + editHistoricoCursosCmd.alumnoId());
        }
    }

/*
    public GetTecnicoDto edit(EditTecnicoCmd editTecnicoCmd, Long id) {

        List<GetIncidenciaBasicaDto> listaIncidencias =
                incidenciaRepository.findIncidenciasByTecnicoId(id);

        List<GetIncidenciaBasicaDto> listaIncidenciasTecnico =
                incidenciaRepository.findIncidenciasTecnicoByTecnicoId(id);

        Tecnico aEditar = tecnicoRepository.findById(id)
                .map(old -> {
                    old.setNombre(editTecnicoCmd.nombre());
                    old.setUsername(editTecnicoCmd.username());
                    old.setEmail(editTecnicoCmd.email());
                    old.setPassword(editTecnicoCmd.password());
                    old.setRole(editTecnicoCmd.role());
                    return tecnicoRepository.save(old);
                }).orElseThrow(() -> new EntityNotFoundException("No existe ningún Técnico con ID: " + id));

        return GetTecnicoDto.of(aEditar, listaIncidencias, listaIncidenciasTecnico);
    }*/
/*
    public void delete(Long id) {
        tecnicoRepository.deleteById(id);
    }*/
}

