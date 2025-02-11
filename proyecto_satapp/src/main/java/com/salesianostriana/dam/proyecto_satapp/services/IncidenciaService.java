package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.EditIncidenciaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.EditPersonalCmd;
import com.salesianostriana.dam.proyecto_satapp.models.*;
import com.salesianostriana.dam.proyecto_satapp.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final EquipoRepository equipoRepository;
    private final UbicacionRepository ubicacionRepository;


    public List<GetIncidenciaBasicaDto> findAll() {
        List<GetIncidenciaBasicaDto> result = incidenciaRepository.findAllIncidenciasBasicasDto();
        if (result.isEmpty())
            throw new EntityNotFoundException("No existen incidencias con esos criterios de búsqueda");
        return result;
    }

    public Incidencia findById(Long id) {
        Optional<Incidencia> incidenciaOpt = incidenciaRepository.findById(id);

        if (incidenciaOpt.isPresent()) {
            return incidenciaOpt.get();
        } else {
            throw new EntityNotFoundException("No existe ninguna incidencia con ID: " + id);
        }
    }

    public List<GetIncidenciaSinUsuarioDto> findIncidenciasByUsuarioId(Long id) {
        List<GetIncidenciaSinUsuarioDto> result = incidenciaRepository.findIncidenciasSinUsuarioByUsuarioId(id);
        if (result.isEmpty())
            throw new EntityNotFoundException("No existe ninguna incidencia del usuario con ID: " + id);
        return result;
    }

    public Incidencia save(EditIncidenciaCmd editIncidenciaCmd) {

        Equipo equipo = null;
        Ubicacion ubicacion = null;

        Categoria categoria = categoriaRepository.findById(editIncidenciaCmd.categoriaId())
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna categoría con ID: "
                        + editIncidenciaCmd.categoriaId()));

        Usuario usuario = usuarioRepository.findById(editIncidenciaCmd.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("No existe ningún usuario con ID: "
                        + editIncidenciaCmd.usuarioId()));

        if (editIncidenciaCmd.equipoId() != null) {
            Optional<Equipo> optionalEquipo = equipoRepository.findById(editIncidenciaCmd.equipoId());

            if (optionalEquipo.isPresent()) {
                equipo = optionalEquipo.get();
            } else {
                throw new EntityNotFoundException
                        ("No existe ningún Equipo con ID: " + editIncidenciaCmd.equipoId());
            }
        }

        if (editIncidenciaCmd.ubicacionId() != null) {
            Optional<Ubicacion> optionalUbicacion = ubicacionRepository.findById(editIncidenciaCmd.ubicacionId());

            if (optionalUbicacion.isPresent()) {
                ubicacion = optionalUbicacion.get();
            } else {
                throw new EntityNotFoundException
                        ("No existe ninguna Ubicación con ID: " + editIncidenciaCmd.ubicacionId());
            }
        }

        Incidencia incidencia = Incidencia.builder()
                .fecha(LocalDateTime.now().withNano(0))
                .titulo(editIncidenciaCmd.titulo())
                .descripcion(editIncidenciaCmd.descripcion())
                .estado(editIncidenciaCmd.estado())
                .urgencia(editIncidenciaCmd.urgencia())
                .categoria(categoria)
                .usuario(usuario)
                .equipo(equipo)
                .ubicacion(ubicacion)
                .build();

        return incidenciaRepository.save(incidencia);
    }

    public Incidencia edit(EditIncidenciaCmd editIncidenciaCmd, Long id) {

        Categoria categoria = categoriaRepository.findById(editIncidenciaCmd.categoriaId())
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna categoría con ID: "
                        + editIncidenciaCmd.categoriaId()));

        Equipo equipo = (editIncidenciaCmd.equipoId() != null)
                ? equipoRepository.findById(editIncidenciaCmd.equipoId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe ningún Equipo con ID: " + editIncidenciaCmd.equipoId()))
                : null;

        Ubicacion ubicacion = (editIncidenciaCmd.ubicacionId() != null)
                ? ubicacionRepository.findById(editIncidenciaCmd.ubicacionId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No existe ninguna Ubicación con ID: " + editIncidenciaCmd.ubicacionId()))
                : null;

        return incidenciaRepository.findById(id)
                .map(old -> {
                    old.setFecha(LocalDateTime.now().withNano(0));
                    old.setTitulo(editIncidenciaCmd.titulo());
                    old.setDescripcion(editIncidenciaCmd.descripcion());
                    old.setEstado(editIncidenciaCmd.estado());
                    old.setUrgencia(editIncidenciaCmd.urgencia());
                    old.setCategoria(categoria);
                    old.setEquipo(equipo);
                    old.setUbicacion(ubicacion);
                    return incidenciaRepository.save(old);
                }).orElseThrow(() -> new EntityNotFoundException("No existe ninguna incidencia con ID: " + id));
    }

    public void delete(Long id) {
        incidenciaRepository.deleteById(id);
    }

}