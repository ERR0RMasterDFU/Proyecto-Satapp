package com.salesianostriana.dam.proyecto_satapp.repositories;

import com.salesianostriana.dam.proyecto_satapp.models.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

    /*@Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.CreateUbicacionDto(u.nombre)
            from Ubicacion u join u.listaIncidencias li
            where u.nombre = :tagNombre

           """)
    CreateUbicacionDto infoBasicaProductosPorTag(String tagNombre);*/

}
