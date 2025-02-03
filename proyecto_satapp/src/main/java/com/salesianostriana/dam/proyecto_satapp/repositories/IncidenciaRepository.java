package com.salesianostriana.dam.proyecto_satapp.repositories;

import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    // CONSULTAS PARA USUARIO
    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria.nombre
        )
        from Incidencia i join i.usuario u
        where u.id = ?1
    """)
    List<GetIncidenciaBasicaDto> findIncidenciasByUsuarioId(Long id);

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria.nombre
        )
        from Incidencia i join i.usuario u
        where TYPE(u) = Alumno and u.id = ?1
    """)
    List<GetIncidenciaBasicaDto> findIncidenciasByAlumnoId(Long id);

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria.nombre
        )
        from Incidencia i join i.usuario u
        where TYPE(u) = Personal and u.id = ?1
    """)
    List<GetIncidenciaBasicaDto> findIncidenciasByPersonalId(Long id);

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria.nombre
        )
        from Incidencia i join i.usuario u
        where TYPE(u) = Tecnico and u.id = ?1
    """)
    List<GetIncidenciaBasicaDto> findIncidenciasByTecnicoId(Long id);
}
