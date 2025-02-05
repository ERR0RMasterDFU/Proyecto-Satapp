package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.EditCatgeoriaCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaSinListasDto;
import com.salesianostriana.dam.proyecto_satapp.dto.categoria.GetCategoriaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Categoria;
import com.salesianostriana.dam.proyecto_satapp.repositories.CategoriaRepository;
import com.salesianostriana.dam.proyecto_satapp.repositories.IncidenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final IncidenciaRepository incidenciaRepository;


    // MÉOTDOS NECESARIOS PARA CONVERSIÓN A DTO EN EL CONTROLADOR ------------------------------------------------------------------------------------------------------------

    public List<GetCategoriaBasicaDto> getSubCategoriasDtoById(Long id) {
        return categoriaRepository.findSubCategoriasDtoById(id);
    }

    public List<GetIncidenciaSinCategoriaDto> getIncidenciasByCategoriaId(Long id) {
        return incidenciaRepository.findIncidenciasByCategoriaId(id);
    }


    // MÉOTDOS PARA EL CONTROLADOR (CRUD) ------------------------------------------------------------------------------------------------------------------------------------

    public List<GetCategoriaSinListasDto> findAll() {
        List<GetCategoriaSinListasDto> result = categoriaRepository.findAllCategoriasDto();
        if (result.isEmpty()) {
            throw new EntityNotFoundException("No existen categorías con esos criterios de búsqueda");
        } else {
            return result;
        }
    }

    public Categoria findById(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()) {
            return categoriaOptional.get();
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

    public Categoria edit(EditCatgeoriaCmd editCatgeoriaCmd, Long id) {
        return categoriaRepository.findById(id)
                .map(old -> {
                    old.setNombre(editCatgeoriaCmd.nombre());
                    return categoriaRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna categoría con ID: "+ id));
    }
/*
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
*/

}
