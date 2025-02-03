package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.EditEquipoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;
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
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final UbicacionRepository ubicacionRepository;


    public List<GetEquipoSinListasDto> findAll() {
        List<GetEquipoSinListasDto> result = equipoRepository.findAllEquiposSinListasDto();
        if (result.isEmpty())
            throw new EntityNotFoundException("No existen equipos con esos criterios de búsqueda");
        return result;
    }

    public GetEquipoDto findById(Long id) {
        Optional<Equipo> equipoOptional = equipoRepository.findById(id);
        // FALTAN LAS INCIDENCIAS
        if (equipoOptional.isEmpty()) {
            throw new EntityNotFoundException("No existe ningún Equipo con ID: " + id);
        } else {
            return GetEquipoDto.of(equipoOptional.get());
        }
    }

    public Equipo save(EditEquipoCmd editEquipoCmd) {

        Optional<Ubicacion> ubicacionOpt = ubicacionRepository.findById(editEquipoCmd.ubicacionId());
        Ubicacion ubicacion = null;

        if (ubicacionOpt.isPresent()) {
            ubicacion = ubicacionOpt.get();
        } else {
            throw new EntityNotFoundException("No existe ninguna Ubicacion con ID: " + editEquipoCmd.ubicacionId());
        }

        Equipo equipo = Equipo.builder()
                .nombre(editEquipoCmd.nombre())
                .caracteristicas(editEquipoCmd.caracteristicas())
                .ubicacion(ubicacion)
                .build();

        return equipoRepository.save(equipo);
    }

    public Equipo edit(EditEquipoCmd editEquipoCmd, Long id) {

        Ubicacion ubicacionAEditar = ubicacionRepository.findById(editEquipoCmd.ubicacionId())
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna Ubicación con ID: " + editEquipoCmd.ubicacionId()));

        return equipoRepository.findById(id)
                .map(old -> {
                    old.setNombre(editEquipoCmd.nombre());
                    old.setCaracteristicas(editEquipoCmd.caracteristicas());
                    old.setUbicacion(ubicacionAEditar);
                    return equipoRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ningún Equipo con ID: "+ id));
    }

    public void delete(Long id) {
        equipoRepository.deleteById(id);
    }

}
