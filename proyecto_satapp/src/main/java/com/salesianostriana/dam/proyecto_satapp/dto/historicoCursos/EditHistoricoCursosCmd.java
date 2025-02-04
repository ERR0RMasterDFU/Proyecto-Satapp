package com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos;

public record EditHistoricoCursosCmd(
    Long alumnoId,
    String curso,
    String cursoEscolar
){
}
