package com.salesianostriana.dam.proyecto_satapp.models;

import lombok.*;
import java.io.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HistoricoCursosPk implements Serializable {

    private Alumno alumno;
    private String cursoEscolar;

}
