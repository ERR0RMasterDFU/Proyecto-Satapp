package com.salesianostriana.dam.proyecto_satapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class HistoricoCursos {

    @Id // EL ID ESTÁ PARA QUE NO DE ERROR,
        // PERO EN VERDAD ES UNA ASOCIACIÓN
    private String cursoEscolar;
    private int curso;


}
