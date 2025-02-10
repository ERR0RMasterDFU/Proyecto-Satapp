package com.salesianostriana.dam.proyecto_satapp.repositories;

import com.salesianostriana.dam.proyecto_satapp.dto.historicoCursos.GetHistoricoCursosBasicoDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinCategoriaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinUsuarioDto;
import com.salesianostriana.dam.proyecto_satapp.dto.nota.GetNotaBasicaDto;
import com.salesianostriana.dam.proyecto_satapp.dto.nota.GetNotaDto;
import com.salesianostriana.dam.proyecto_satapp.models.HistoricoCursos;
import com.salesianostriana.dam.proyecto_satapp.models.Incidencia;
import com.salesianostriana.dam.proyecto_satapp.models.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria
        )
        from Incidencia i join i.usuario u
        where u.id = ?1
    """)
    List<GetIncidenciaBasicaDto> findIncidenciasByUsuarioId(Long id);

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria
        )
        from Incidencia i join i.usuario u
        where TYPE(u) = Alumno and u.id = ?1
    """)
    List<GetIncidenciaBasicaDto> findIncidenciasByAlumnoId(Long id);

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria
        )
        from Incidencia i join i.usuario u
        where TYPE(u) = Personal and u.id = ?1
    """)
    List<GetIncidenciaBasicaDto> findIncidenciasByPersonalId(Long id);

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria
        )
        from Incidencia i join i.usuario u
        where TYPE(u) = Tecnico and u.id = ?1
    """)
    List<GetIncidenciaBasicaDto> findIncidenciasByTecnicoId(Long id);

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria
        )
        from Tecnico t join t.listaIncidenciasTecnico i
        where t.id = ?1
    """)
    List<GetIncidenciaBasicaDto> findIncidenciasTecnicoByTecnicoId(Long id);

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria
        )
        from Incidencia i join i.ubicacion u
        where u.id = ?1
    """)
    List<GetIncidenciaBasicaDto> findIncidenciasByUbicacionId(Long id);

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinCategoriaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia
        )
        from Incidencia i join i.categoria c
        where c.id = ?1
    """)
    List<GetIncidenciaSinCategoriaDto> findIncidenciasByCategoriaId(Long id);

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria
        )
        from Incidencia i join i.equipo e
        where e.id = ?1
    """)
    List<GetIncidenciaBasicaDto> findIncidenciasByEquipoId(Long id);

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.nota.GetNotaBasicaDto(
            n.fecha, n.autor, n.contenido
        ) 
        from Nota n
        where n.incidencia.id = ?1
    """)
    List<GetNotaBasicaDto> findNotasByIncidenciaId(Long id);

    @Query("""
        select n
        from Nota n
        where n.incidencia.id = ?1 and n.fecha = ?2 and n.autor = ?3
    """)
    Optional<Nota> findNotasByIncidenciaIdAndFechaAndAutor(Long idIncidencia, LocalDateTime fecha, String autor);

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaBasicaDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria
        )
        from Incidencia i
        order by i.id asc
    """)
    List<GetIncidenciaBasicaDto> findAllIncidenciasBasicasDto();

    @Query("""
        select new com.salesianostriana.dam.proyecto_satapp.dto.incidencia.GetIncidenciaSinUsuarioDto(
            i.id, i.fecha, i.titulo, i.descripcion, i.estado, i.urgencia, i.categoria, 
                i.equipo, i.ubicacion
        )
        from Incidencia i join i.usuario u
        where u.id = ?1
    """)
    List<GetIncidenciaSinUsuarioDto> findIncidenciasSinUsuarioByUsuarioId(Long id);
}
