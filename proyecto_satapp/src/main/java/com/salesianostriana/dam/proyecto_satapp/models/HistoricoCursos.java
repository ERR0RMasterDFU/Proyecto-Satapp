package com.salesianostriana.dam.proyecto_satapp.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@IdClass(HistoricoCursosPk.class)
public class HistoricoCursos {

    // COMPOSICIÓN CON ID COMP (M:1) ALUMNO
    @Id
    @ManyToOne
    @JoinColumn(name="alumno_id", foreignKey = @ForeignKey(name = "fk_historicoCursos_alumno"))
    private Alumno alumno;

    @Id
    private String cursoEscolar;

    @Id
    private String curso;


    // EQUALS & HASH CODE ----------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        HistoricoCursos that = (HistoricoCursos) o;
        return false;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(alumno, cursoEscolar, curso);
    }
}
