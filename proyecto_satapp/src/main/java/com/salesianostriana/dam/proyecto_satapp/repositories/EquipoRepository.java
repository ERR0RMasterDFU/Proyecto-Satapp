package com.salesianostriana.dam.proyecto_satapp.repositories;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoConUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoConUbicacionDto(
        e.id, e.nombre, e.caracteristicas, e.ubicacion
        )
        from Equipo e
    """)
    List<GetEquipoConUbicacionDto> findAllEquiposConUbicacionDto();

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoDto(
        e.nombre, e.caracteristicas
        )
        from Equipo e join e.ubicacion u
        where u.id = ?1
    """)
    List<GetEquipoDto> findEquiposByUbicacionId(Long id);

}
