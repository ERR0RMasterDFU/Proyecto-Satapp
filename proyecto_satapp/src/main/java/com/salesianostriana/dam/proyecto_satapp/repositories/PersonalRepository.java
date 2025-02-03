package com.salesianostriana.dam.proyecto_satapp.repositories;

import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonalRepository extends JpaRepository<Personal, Long> {

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.usuarios.personal.GetPersonalBasicoDto(
            p.id, p.nombre, p.username, p.email, p.role, p.tipo
        ) 
        from Personal p
    """)
    List<GetPersonalBasicoDto> findAllBasicoDto();



}
