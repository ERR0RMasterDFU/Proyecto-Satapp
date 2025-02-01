package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.EditCatgeoriaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetSubCategoriaDto;
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


    public List<GetCategoriaSinListasDto> findAllDto() {
        List<GetCategoriaSinListasDto> result = categoriaRepository.findAllCategoriasDto();
        if (result.isEmpty())
            throw new EntityNotFoundException("No existen categorías con esos criterios de búsqueda");
        return result;
    }

    public GetCategoriaDto findByIdDto(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()) {

            Categoria categoria = categoriaOptional.get();

            List<GetSubCategoriaDto> listaSubCategoriaDto =
                    categoriaRepository.findSubCategoriasDtoById(id);

            // FALTAN LAS INCIDENCIAS

            return GetCategoriaDto.of(categoria, listaSubCategoriaDto);

        } else {
            throw new EntityNotFoundException("No existe ninguna Categoría con ID: " + id);
        }
    }

    /*
    public List<Categoria> findAll() {
        List<Categoria> result = categoriaRepository.findAll();
        if (result.isEmpty())
            throw new EntityNotFoundException("No existen categorías con esos criterios de búsqueda");
        return result;
    }

    public Categoria findById(Long id) {
        Optional<Categoria> ubicacionOptional = categoriaRepository.findById(id);

        if (ubicacionOptional.isPresent()) {
            return ubicacionOptional.get();
        } else {
            throw new EntityNotFoundException("No existe ninguna Categoría con ID: " + id);
        }
    }*/

    public Categoria save(EditCatgeoriaCmd editCategoriaCmd) {
        Categoria categoria = new Categoria();
        categoria.setNombre(editCategoriaCmd.nombre());

        return categoriaRepository.save(categoria);
    }
/*
    public Ubicacion findByIdConDto(Long id) {
        Optional<Ubicacion> ubicacionOptional = ubicacionRepository.findById(id);

        if (ubicacionOptional.isPresent()) {
            return ubicacionOptional.get();
        } else {
            throw new EntityNotFoundException("No existe ninguna Ubicacion con ID: " + id);
        }
    }*/

    public Categoria edit(EditCatgeoriaCmd categoria, Long id) {
        return categoriaRepository.findById(id)
                .map(old -> {
                    old.setNombre(categoria.nombre());
                    return categoriaRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna categoría con ID: "+ id));
    }
/*
    public void delete(Long id) {
        ubicacionRepository.deleteById(id);
    }
*/

}
