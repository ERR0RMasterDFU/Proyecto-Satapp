package com.salesianostriana.dam.proyecto_satapp.repositories;

import com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionDto;
import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.ubicacion.GetUbicacionDto(
            u.nombre
        )
        from Ubicacion u
    """)
    List<GetUbicacionDto> findAllSinListas();




//u join u.listaIncidencias join u.listaEquipos
}
