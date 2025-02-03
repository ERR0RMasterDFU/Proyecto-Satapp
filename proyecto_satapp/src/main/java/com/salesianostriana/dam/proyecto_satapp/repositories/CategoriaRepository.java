package com.salesianostriana.dam.proyecto_satapp.repositories;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaSinListasDto(
            c.id, c.nombre, COALESCE(c.categoriaPadre.nombre, 'Ninguna')
        )
        from Categoria c left join c.categoriaPadre
    """)
    List<GetCategoriaSinListasDto> findAllCategoriasDto();

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaBasicaDto(
            c.id, c.nombre
        )
        from Categoria c
        where c.categoriaPadre.id = ?1
    """)
    List<GetCategoriaBasicaDto> findSubCategoriasDtoById(Long id);

}
