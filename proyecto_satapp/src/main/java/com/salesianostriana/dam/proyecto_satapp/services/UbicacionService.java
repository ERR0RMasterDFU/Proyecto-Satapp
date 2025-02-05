package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.EditUbicacionCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.error.UbicacionEnUsoException;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;
import com.salesianostriana.dam.proyecto_satapp.repositories.EquipoRepository;
import com.salesianostriana.dam.proyecto_satapp.repositories.IncidenciaRepository;
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
    private final IncidenciaRepository incidenciaRepository;


    // MÉOTDOS NECESARIOS PARA CONVERSIÓN A DTO EN EL CONTROLADOR ------------------------------------------------------------------------------------------------------------

    public List<GetEquipoBasicoDto> getEquiposByUbicacionId(Long id) {
        return equipoRepository.findEquiposByUbicacionId(id);
    }

    public List<GetIncidenciaBasicaDto> getIncidenciasByUbicacionId(Long id) {
        return incidenciaRepository.findIncidenciasByUbicacionId(id);
    }


    // MÉOTDOS PARA EL CONTROLADOR (CRUD) ------------------------------------------------------------------------------------------------------------------------------------

    public List<GetUbicacionSinListasDto> findAll() {
        List<GetUbicacionSinListasDto> result = ubicacionRepository.findAllSinListas();
        if(result.isEmpty()) {
            throw new EntityNotFoundException("No existen ubicaciones con esos criterios de búsqueda");
        } else {
            return result;
        }
    }

    public Ubicacion findById(Long id) {
        Optional<Ubicacion> ubicacionOptional = ubicacionRepository.findById(id);

        if (ubicacionOptional.isPresent()) {
            return ubicacionOptional.get();
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

    public Ubicacion edit(EditUbicacionCmd editUbicacionCmd, Long id) {
        return ubicacionRepository.findById(id)
                .map(old -> {
                    old.setNombre(editUbicacionCmd.nombre());
                    return ubicacionRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna Ubicacion con ID: "+ id));
    }

    public void delete(Long id) {
        Ubicacion ubicacion = findById(id);

        if(ubicacion.getListaEquipos().isEmpty() && ubicacion.getListaIncidencias().isEmpty()) {
            ubicacionRepository.deleteById(id);
        } else {
            if(!ubicacion.getListaEquipos().isEmpty() && !ubicacion.getListaIncidencias().isEmpty()) {
                throw new UbicacionEnUsoException
                        ("No puedes eliminar esta ubicación porque hay equipos en ella e incidencias asociadas.");
            } else if(!ubicacion.getListaEquipos().isEmpty()) {
                throw new UbicacionEnUsoException
                        ("No puedes eliminar esta ubicación porque hay equipos en ella.");
            } else {
                throw new UbicacionEnUsoException
                        ("No puedes eliminar esta ubicación porque hay incicidencias asociadas.");
            }
        }
    }

}
