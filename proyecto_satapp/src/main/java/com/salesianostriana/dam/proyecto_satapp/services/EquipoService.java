package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.EditEquipoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;
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
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final IncidenciaRepository incidenciaRepository;
    private final UbicacionRepository ubicacionRepository;


    // MÉOTDOS NECESARIOS PARA CONVERSIÓN A DTO EN EL CONTROLADOR ------------------------------------------------------------------------------------------------------------

    public List<GetIncidenciaBasicaDto> getIncidenciasByEquipoId(Long id) {
        return incidenciaRepository.findIncidenciasByEquipoId(id);
    }


    // MÉOTDOS PARA EL CONTROLADOR (CRUD) ------------------------------------------------------------------------------------------------------------------------------------

    public List<GetEquipoSinListasDto> findAll() {
        List<GetEquipoSinListasDto> result = equipoRepository.findAllEquiposSinListasDto();
        if (result.isEmpty()) {
            throw new EntityNotFoundException("No existen equipos con esos criterios de búsqueda");
        } else {
            return result;
        }
    }

    public Equipo findById(Long id) {
        Optional<Equipo> equipoOptional = equipoRepository.findById(id);

        if (equipoOptional.isPresent()) {
            return equipoOptional.get();
        } else {
            throw new EntityNotFoundException("No existe ningún Equipo con ID: " + id);
        }
    }

    public Equipo save(EditEquipoCmd editEquipoCmd) {

        Optional<Ubicacion> ubicacionOpt = ubicacionRepository.findById(editEquipoCmd.ubicacionId());

        if (ubicacionOpt.isPresent()) {
            Equipo equipo = Equipo.builder()
                    .nombre(editEquipoCmd.nombre())
                    .caracteristicas(editEquipoCmd.caracteristicas())
                    .ubicacion(ubicacionOpt.get())
                    .build();

            return equipoRepository.save(equipo);
        } else {
            throw new EntityNotFoundException("No existe ninguna Ubicacion con ID: " + editEquipoCmd.ubicacionId());
        }
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
