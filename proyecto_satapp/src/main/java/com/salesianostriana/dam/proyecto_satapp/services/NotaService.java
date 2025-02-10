package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.nota.CreateNotaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.nota.EditNotaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.nota.GetNotaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.*;
import com.salesianostriana.dam.proyecto_satapp.repositories.IncidenciaRepository;
import com.salesianostriana.dam.proyecto_satapp.repositories.TecnicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final IncidenciaRepository incidenciaRepository;
    private final TecnicoRepository tecnicoRepository;


    public List<GetNotaBasicaDto> getNotasByIncidenciaId(Long id) {

        List<GetNotaBasicaDto> listaNotas = incidenciaRepository.findNotasByIncidenciaId(id);

        if (listaNotas.isEmpty()) {
            throw new EntityNotFoundException("No existen Notas con esos criterios de búsqueda");
        }else{
            return listaNotas;
        }
    }

    public Nota save(CreateNotaCmd createNotaCmd) {

        Optional<Incidencia> incidencia =
                incidenciaRepository.findById(createNotaCmd.incidenciaId());

        Optional<Tecnico> tecnico =
                tecnicoRepository.findById(createNotaCmd.tecnicoId());

        if (incidencia.isPresent() && tecnico.isPresent()) {
            Nota nota = Nota.builder()
                    .incidencia(incidencia.get())
                    .fecha(LocalDateTime.now().withNano(0))
                    .autor(tecnico.get().getNombre())
                    .contenido(createNotaCmd.contenido())
                    .build();

            incidencia.get().addNota(nota);
            incidenciaRepository.save(incidencia.get());
            return nota;

        } else {
            if(incidencia.isEmpty()) {
                throw new EntityNotFoundException("No existe ninguna Incidencia con ID: " + createNotaCmd.incidenciaId());
            } else {
                throw new EntityNotFoundException("No existe ningún Técnico con ID: " + createNotaCmd.tecnicoId());
            }
        }
    }

    public Nota edit(EditNotaCmd editNotaCmd, Long idIncidencia, String fecha, Long idTecnico) {

        LocalDateTime fechaNota = LocalDateTime.parse(fecha).withNano(0);

        Incidencia incidencia = incidenciaRepository.findById(idIncidencia)
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna Incidencia con ID: " + idIncidencia));

        Tecnico autor = tecnicoRepository.findById(idTecnico)
                .orElseThrow(() -> new EntityNotFoundException("No existe ningún Técnico (autor) con ID: " + idTecnico));

        Nota nota = incidenciaRepository.findNotasByIncidenciaIdAndFechaAndAutor(incidencia.getId(), fechaNota, autor.getNombre())
                .orElseThrow(() -> new EntityNotFoundException("No existe una nota asociada a una incidencia con ID: " +
                        idIncidencia + ", fecha: " + LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                        + " y técnico (autor) con ID: " + idTecnico));

        nota.setContenido(editNotaCmd.contenido());
        incidenciaRepository.save(incidencia);

        return nota;
    }

    public void delete(Long idIncidencia, String fecha, Long idTecnico) {

        LocalDateTime fechaNota = LocalDateTime.parse(fecha).withNano(0);

        Incidencia incidencia = incidenciaRepository.findById(idIncidencia)
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna Incidencia con ID: " + idIncidencia));

        Tecnico autor = tecnicoRepository.findById(idTecnico)
                .orElseThrow(() -> new EntityNotFoundException("No existe ningún Técnico (autor) con ID: " + idTecnico));

        Nota nota = incidenciaRepository.findNotasByIncidenciaIdAndFechaAndAutor(incidencia.getId(), fechaNota, autor.getNombre())
                .orElseThrow(() -> new EntityNotFoundException("No existe una nota asociada a una incidencia con ID: " +
                        idIncidencia + ", fecha: " + LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                        + " y técnico (autor) con ID: " + idTecnico));

        incidencia.removeNota(nota);
        incidenciaRepository.save(incidencia);
    }

}
