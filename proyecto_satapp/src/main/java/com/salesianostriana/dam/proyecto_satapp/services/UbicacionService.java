package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.GetUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;
import com.salesianostriana.dam.proyecto_satapp.repositories.UbicacionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UbicacionService {

    private final UbicacionRepository UbicacionRepository;


    public List<Ubicacion> findAll() {
        List<Ubicacion> result = UbicacionRepository.findAll();
        if (result.isEmpty())
            throw new EntityNotFoundException("No existen ubicaciones con esos criterios de búsqueda");
        return result;
    }

    public List<GetUbicacionDto> findAllSinListas() {
        List<GetUbicacionDto> result = UbicacionRepository.findAllSinListas();
        if (result.isEmpty())
            throw new EntityNotFoundException("No existen ubicaciones con esos criterios de búsqueda");
        return result;
    }

    public Ubicacion findById(Long id) {
        Optional<Ubicacion> ubicacionOptional = UbicacionRepository.findById(id);

        if (ubicacionOptional.isPresent()) {
            return ubicacionOptional.get();
        } else {
            throw new EntityNotFoundException("No existe ninguna Ubicacion con ID: " + id);
        }
    }

    public Ubicacion save(Ubicacion ubicacion) {
        return UbicacionRepository.save(ubicacion);
    }

    public Ubicacion edit(Ubicacion ubicacion, Long id) {
        return UbicacionRepository.findById(id)
                .map(old -> {
                    old.setNombre(ubicacion.getNombre());
                    return UbicacionRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna Ubicacion con ID: "+ id));
    }

    public void delete(Long id) {
        UbicacionRepository.deleteById(id);
    }

}
