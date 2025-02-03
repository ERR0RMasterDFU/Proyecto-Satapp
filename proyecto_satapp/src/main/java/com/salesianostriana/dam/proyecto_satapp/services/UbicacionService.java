package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.EditUbicacionCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.error.UbicacionEnUsoException;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;
import com.salesianostriana.dam.proyecto_satapp.repositories.EquipoRepository;
import com.salesianostriana.dam.proyecto_satapp.repositories.UbicacionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;
    private final EquipoRepository equipoRepository;


    public List<GetUbicacionSinListasDto> findAll() {
        List<GetUbicacionSinListasDto> result = ubicacionRepository.findAllSinListas();
        if (result.isEmpty())
            throw new EntityNotFoundException("No existen ubicaciones con esos criterios de búsqueda");
        return result;
    }

    public GetUbicacionDto findById(Long id) {
        Optional<Ubicacion> ubicacionOptional = ubicacionRepository.findById(id);

        if (ubicacionOptional.isPresent()) {
            List<GetEquipoBasicoDto> listaEquipos = equipoRepository.findEquiposByUbicacionId(id);
            // FALTAN INCIDENCIAS
            return GetUbicacionDto.of(ubicacionOptional.get(), listaEquipos);
        } else {
            throw new EntityNotFoundException("No existe ninguna Ubicación con ID: " + id);
        }
    }

    public Ubicacion save(EditUbicacionCmd editUbicacionCmd) {
        Ubicacion nuevaUbicacion = Ubicacion.builder()
                .nombre(editUbicacionCmd.nombre())
                .build();
        return ubicacionRepository.save(nuevaUbicacion);
    }

    public GetUbicacionDto edit(EditUbicacionCmd editUbicacionCmd, Long id) {
        List<GetEquipoBasicoDto> listaEquipos = equipoRepository.findEquiposByUbicacionId(id);
        // FALTAN LAS INCIDENCIAS
        Ubicacion ubicacionAEditar = ubicacionRepository.findById(id)
                .map(old -> {
                    old.setNombre(editUbicacionCmd.nombre());
                    return ubicacionRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna Ubicacion con ID: "+ id));

        return GetUbicacionDto.of(ubicacionAEditar, listaEquipos);
    }

    public void delete(Long id) {
        GetUbicacionDto ubicacion = findById(id);

        if(ubicacion.listaEquipos().isEmpty() //&& ubicacion.listaIncidencias().isEmpty
        ) {
            ubicacionRepository.deleteById(id);
        } else {
            if(!ubicacion.listaEquipos().isEmpty() //&& !ubicacion.listaIncidencias().isEmpty
            ) {
                throw new UbicacionEnUsoException("No puedes eliminar esta ubicación porque hay equipos en ella e incidencias asociadas.");
            } else if(!ubicacion.listaEquipos().isEmpty()) {
                throw new UbicacionEnUsoException("No puedes eliminar esta ubicación porque hay equipos en ella.");
            } else {
                throw new UbicacionEnUsoException("No puedes eliminar esta ubicación porque hay incicidencias asociadas.");
            }
        }
    }

}
