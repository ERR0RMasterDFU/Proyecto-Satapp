package com.salesianostriana.dam.proyecto_satapp.models;

import lombok.*;
import java.io.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoCursosPk implements Serializable {

    private Alumno alumno;
    private String cursoEscolar;
    private String curso;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoCursosPk that = (HistoricoCursosPk) o;
        return curso == that.curso &&
                Objects.equals(alumno, that.alumno) &&
                Objects.equals(cursoEscolar, that.cursoEscolar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumno, cursoEscolar, curso);
    }
}
