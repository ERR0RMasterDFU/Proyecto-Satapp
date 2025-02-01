package com.salesianostriana.dam.proyecto_satapp.services;

import com.salesianostriana.dam.proyecto_satapp.dto.categoria.EditCatgeoriaCmd;
import com.salesianostriana.dam.proyecto_satapp.models.Categoria;
import com.salesianostriana.dam.proyecto_satapp.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    /*
    public List<Categoria> findAll() {
        List<Categoria> result = CategoriaRepository.findAll();
        if (result.isEmpty())
            throw new EntityNotFoundException("No existen categorías con esos criterios de búsqueda");
        return result;
    }

    public Ubicacion findById(Long id) {
        Optional<Ubicacion> ubicacionOptional = ubicacionRepository.findById(id);

        if (ubicacionOptional.isPresent()) {
            return ubicacionOptional.get();
        } else {
            throw new EntityNotFoundException("No existe ninguna Ubicacion con ID: " + id);
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
    }

    public Ubicacion edit(Ubicacion ubicacion, Long id) {
        return ubicacionRepository.findById(id)
                .map(old -> {
                    old.setNombre(ubicacion.getNombre());
                    return ubicacionRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No existe ninguna Ubicacion con ID: "+ id));
    }

    public void delete(Long id) {
        ubicacionRepository.deleteById(id);
    }
*/

}
