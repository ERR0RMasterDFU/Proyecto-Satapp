package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.CreateEquipoCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.EditUbicacionCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionDto;
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

/*
    public List<Ubicacion> findAll() {
        List<Ubicacion> result = ubicacionRepository.findAll();
        if (result.isEmpty())
            throw new EntityNotFoundException("No existen ubicaciones con esos criterios de búsqueda");
        return result;
    }

    public List<GetUbicacionDto> findAllSinListas() {
        List<GetUbicacionDto> result = ubicacionRepository.findAllSinListas();
        if (result.isEmpty())
            throw new EntityNotFoundException("No existen ubicaciones con esos criterios de búsqueda");
        return result;
    }

    public Ubicacion findById(Long id) {
        Optional<Ubicacion> ubicacionOptional = ubicacionRepository.findById(id);

        if (ubicacionOptional.isPresent()) {
            return ubicacionOptional.get();
        } else {
            throw new EntityNotFoundException("No existe ninguna Ubicacion con ID: " + id);
        }
    }*/

    public Equipo save(CreateEquipoCmd createEquipoCmd) {

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

    /*public Ubicacion edit(Ubicacion ubicacion, Long id) {
        return ubicacionRepository.findById(id)
                .map(old -> {
                    old.setNombre(ubicacion.getNombre());
                    return ubicacionRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna Ubicacion con ID: "+ id));
    }

    public Ubicacion edit(EditUbicacionCmd editProductoCmd, Long id) {
        return ubicacionRepository.findById(id)
                .map(old -> {
                    old.setNombre(editProductoCmd.nombre());
                    return ubicacionRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna Ubicacion con ID: "+ id));

    }

    public void delete(Long id) {
        ubicacionRepository.deleteById(id);
    }*/

}
