package com.salesianostriana.dam.proyecto_satapp.repositories;

import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoSinListasDto(
        e.nombre, e.caracteristicas, e.ubicacion.nombre
        )
        from Equipo e
    """)
    List<GetEquipoSinListasDto> findAllEquiposSinListasDto();

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.equipo.GetEquipoBasicoDto(
        e.nombre, e.caracteristicas
        )
        from Equipo e join e.ubicacion u
        where u.id = ?1
    """)
    List<GetEquipoBasicoDto> findEquiposByUbicacionId(Long id);

}
