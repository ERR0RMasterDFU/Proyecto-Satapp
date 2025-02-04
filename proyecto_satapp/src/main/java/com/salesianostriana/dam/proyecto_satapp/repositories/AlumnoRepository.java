package com.salesianostriana.dam.proyecto_satapp.repositories;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoBasicoDto(
            a.id, a.nombre, a.username, a.email, a.role
        ) 
        from Alumno a
    """)
    List<GetAlumnoBasicoDto> findAllBasicoDto();

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto(
            hu.curso, hu.cursoEscolar
        ) 
        from HistoricoCursos hu 
        where hu.alumno.id = ?1
    """)
    List<GetHistoricoCursosBasicoDto> findHistoricoCursosByAlumnoId(Long id);

}
