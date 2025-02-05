package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.EditIncidenciaCmd;
import com.salesianostriana.dam.proyecto_satapp.models.*;
import com.salesianostriana.dam.proyecto_satapp.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EquipoRepository equipoRepository;
    private final UbicacionRepository ubicacionRepository;


    public Incidencia save(EditIncidenciaCmd editIncidenciaCmd) {

        Equipo equipo = null;
        Ubicacion ubicacion = null;

        Optional<Categoria> optionalCategoria = categoriaRepository.findById(editIncidenciaCmd.categoriaId());

        if(optionalCategoria.isEmpty()) {
            throw new EntityNotFoundException
                    ("No existe ninguna Categoria con ID: " + editIncidenciaCmd.categoriaId());
        }

    /*
        if(editIncidenciaCmd.usuarioId() != null) {
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(editIncidenciaCmd.usuarioId());

            if(optionalUsuario.isPresent()) {
                Usuario
            } else {
                incidencia.setUsuario(optionalUsuario.get());
                throw new EntityNotFoundException
                        ("No existe ningún Usuario con ID: " + editIncidenciaCmd.usuarioId());
            }
        }*/

        if(editIncidenciaCmd.equipoId() != null) {
            Optional<Equipo> optionalEquipo = equipoRepository.findById(editIncidenciaCmd.equipoId());

            if(optionalEquipo.isPresent()) {
                equipo = optionalEquipo.get();
            } else {
                throw new EntityNotFoundException
                        ("No existe ningún Equipo con ID: " + editIncidenciaCmd.equipoId());
            }
        }

        if(editIncidenciaCmd.ubicacionId() != null) {
            Optional<Ubicacion> optionalUbicacion = ubicacionRepository.findById(editIncidenciaCmd.ubicacionId());

            if(optionalUbicacion.isPresent()) {
                ubicacion = optionalUbicacion.get();
            } else {
                throw new EntityNotFoundException
                        ("No existe ninguna Ubicación con ID: " + editIncidenciaCmd.ubicacionId());
            }
        }

        Incidencia incidencia = Incidencia.builder()
                .fecha(LocalDateTime.now())
                .titulo(editIncidenciaCmd.titulo())
                .descripcion(editIncidenciaCmd.descripcion())
                .estado(editIncidenciaCmd.estado())
                .urgencia(editIncidenciaCmd.urgencia())
                .categoria(optionalCategoria.get())
                .equipo(equipo)
                .ubicacion(ubicacion)
                .build();

        return incidenciaRepository.save(incidencia);
    }

    /*
    public List<Ubicacion> findAll() {
        List<Ubicacion> result = ubicacionRepository.findAll();
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
    }

    public Ubicacion findByIdConDto(Long id) {
        Optional<Ubicacion> ubicacionOptional = ubicacionRepository.findById(id);

        if (ubicacionOptional.isPresent()) {
            return ubicacionOptional.get();
        } else {
            throw new EntityNotFoundException("No existe ninguna Ubicacion con ID: " + id);
        }
    }

    public Ubicacion edit(Ubicacion ubicacion, Long id) {
        return ubicacionRepository.findById(id)
                .map(old -> {
                    old.setNombre(ubicacion.getNombre());
                    return ubicacionRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna Ubicacion con ID: "+ id));
    }

    public void delete(Long id) {
        ubicacionRepository.deleteById(id);
*/
}
