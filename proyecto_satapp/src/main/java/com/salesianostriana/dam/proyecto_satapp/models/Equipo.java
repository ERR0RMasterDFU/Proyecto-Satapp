package com.salesianostriana.dam.proyecto_satapp.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Equipo {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String caracteristicas;


    // ASOCIACIONES ----------------------------------------------------------------------------------------------------------------------------------------------------------

    // (1:M) INCIDENCIA
    @OneToMany(mappedBy = "equipo",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Incidencia> listaIncidencias = new ArrayList<>();

    // (M:1) UBICACIÓN
    @ManyToOne
    @JoinColumn(name="ubicacion_id", foreignKey = @ForeignKey(name = "fk_equipo_ubicacion"))
    private Ubicacion ubicacion;


    // HELPERS ---------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void addIncidencia (Incidencia i) {
        i.setEquipo(this);
        listaIncidencias.add(i);
    }

    public void removeIncidencia (Incidencia i) {
        listaIncidencias.remove(i);
        i.setEquipo(null);
    }


    // EQUALS & HASH CODE ----------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Equipo equipo = (Equipo) o;
        return getId() != null && Objects.equals(getId(), equipo.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
