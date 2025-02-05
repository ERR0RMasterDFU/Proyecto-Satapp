package com.salesianostriana.dam.proyecto_satapp.repositories;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.EditHistoricoCursosCmd;
import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.usuarios.alumno.GetAlumnoBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.models.Alumno;
import com.salesianostriana.dam.proyecto_satapp.models.HistoricoCursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

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
            hc.curso, hc.cursoEscolar
        ) 
        from HistoricoCursos hc
        where hc.alumno.id = ?1
    """)
    List<GetHistoricoCursosBasicoDto> findHistoricoCursosByAlumnoId(Long id);

    @Query("""
        select hc
        from HistoricoCursos hc
        where hc.alumno.id = ?1 and hc.cursoEscolar = ?2
    """)
    Optional<HistoricoCursos> findHistoricoCursosByAlumnoIdAndCursoEscolar(Long id, String cursoEscolar);

}
