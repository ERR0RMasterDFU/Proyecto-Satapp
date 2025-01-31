package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.EditEquipoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoConUbicacionDto;
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


    public List<GetEquipoConUbicacionDto> findAll() {
        List<GetEquipoConUbicacionDto> result = equipoRepository.findAllEquiposConUbicacionDto();
        if (result.isEmpty())
            throw new EntityNotFoundException("No existen equipos con esos criterios de búsqueda");
        return result;
    }

    public GetEquipoConUbicacionDto findById(Long id) {
        Optional<Equipo> equipoOptional = equipoRepository.findById(id);

        if (equipoOptional.isEmpty()) {
            throw new EntityNotFoundException("No existe ningún equipo con ID: " + id);
        } else {
            return GetEquipoConUbicacionDto.of(equipoOptional.get());
        }
    }

    public Equipo save(EditEquipoCmd createEquipoCmd) {

        Optional<Ubicacion> ubicacionOpt = ubicacionRepository.findById(createEquipoCmd.ubicacionId());

        Ubicacion ubicacion = null;

        if (ubicacionOpt.isPresent()) {
            ubicacion = ubicacionOpt.get();
        } else {
            throw new EntityNotFoundException("No existe ninguna Ubicacion con ID: " + createEquipoCmd.ubicacionId());
        }

        Equipo equipo = new Equipo();
        equipo.setNombre(createEquipoCmd.nombre());
        equipo.setCaracteristicas(createEquipoCmd.caracteristicas());
        equipo.setUbicacion(ubicacion);

        return equipoRepository.save(equipo);
    }
/*
    public Ubicacion edit(Ubicacion ubicacion, Long id) {
        return ubicacionRepository.findById(id)
                .map(old -> {
                    old.setNombre(ubicacion.getNombre());
                    return ubicacionRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna Ubicacion con ID: "+ id));
    }
*/
    public Equipo edit(EditEquipoCmd editEquipoCmd, Long id) {
        return equipoRepository.findById(id)
                .map(old -> {
                    old.setNombre(editEquipoCmd.nombre());
                    old.setCaracteristicas(editEquipoCmd.caracteristicas());

                    Ubicacion nuevaUbicacion = ubicacionRepository.findById(editEquipoCmd.ubicacionId())
                            .orElseThrow(() -> new EntityNotFoundException("No existe ninguna Ubicación con ID: " + editEquipoCmd.ubicacionId()));

                    old.setUbicacion(nuevaUbicacion);
                    return equipoRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ningún Equipo con ID: "+ id));
    }

    public void delete(Long id) {
        equipoRepository.deleteById(id);
    }

}
