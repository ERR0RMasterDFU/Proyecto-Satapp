package com.salesianostriana.dam.proyecto_satapp.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@IdClass(NotaPk.class)
public class Nota {

    // COMPOSICIÓN CON ID COMP (M:1) INCIDENCIA
    @Id
    @ManyToOne
    @JoinColumn(name="incidencia_id", foreignKey = @ForeignKey(name = "fk_nota_incidencia"))
    private Incidencia incidencia;

    @Id
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fecha;

    @Id
    private String autor;

    private String contenido;


    // EQUALS & HASH CODE ----------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Nota nota = (Nota) o;
        return getIncidencia() != null && Objects.equals(getIncidencia(), nota.getIncidencia())
                && getFecha() != null && Objects.equals(getFecha(), nota.getFecha())
                && getAutor() != null && Objects.equals(getAutor(), nota.getAutor());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(incidencia, fecha, autor);
    }
}
