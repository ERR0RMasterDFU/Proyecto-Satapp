package com.salesianostriana.dam.proyecto_satapp.repositories;

import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico.GetTecnicoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.usuario.GetUsuarioBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.usuarios.tecnico.GetTecnicoBasicoDto(
            t.id, t.nombre, t.username, t.email, t.role
        ) 
        from Tecnico t
    """)
    List<GetTecnicoBasicoDto> findAllBasicoDto();

}
