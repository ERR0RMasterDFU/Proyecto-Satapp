package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.EditCatgeoriaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Categoria;
import com.salesianostriana.dam.proyecto_satapp.repositories.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;


    public List<GetCategoriaSinListasDto> findAll() {
        List<GetCategoriaSinListasDto> result = categoriaRepository.findAllCategoriasDto();
        if (result.isEmpty())
            throw new EntityNotFoundException("No existen categorías con esos criterios de búsqueda");
        return result;
    }

    public GetCategoriaDto findById(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();

            List<GetCategoriaBasicaDto> listaSubCategoriaDto =
                    categoriaRepository.findSubCategoriasDtoById(id);

            // FALTAN LAS INCIDENCIAS

            return GetCategoriaDto.of(categoria, listaSubCategoriaDto);

        } else {
            throw new EntityNotFoundException("No existe ninguna Categoría con ID: " + id);
        }
    }

    public Categoria save(EditCatgeoriaCmd editCategoriaCmd) {
        Categoria categoria = Categoria.builder()
                .nombre(editCategoriaCmd.nombre())
                .build();
        return categoriaRepository.save(categoria);
    }

    public GetCategoriaDto edit(EditCatgeoriaCmd editCatgeoriaCmd, Long id) {

        List<GetCategoriaBasicaDto> listaSubCategoriaDto =
                categoriaRepository.findSubCategoriasDtoById(id);

        // FALTAN INCIDENCIAS

        Categoria categoriaAEditar = categoriaRepository.findById(id)
                .map(old -> {
                    old.setNombre(editCatgeoriaCmd.nombre());
                    return categoriaRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna categoría con ID: "+ id));

        return GetCategoriaDto.of(categoriaAEditar, listaSubCategoriaDto);
    }
/*
    public void delete(Long id) {
        ubicacionRepository.deleteById(id);
    }
*/

}
