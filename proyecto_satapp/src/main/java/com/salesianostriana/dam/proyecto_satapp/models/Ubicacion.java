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
public class Ubicacion {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;


    // ASOCIACIONES ----------------------------------------------------------------------------------------------------------------------------------------------------------

    // (1:M) EQUIPO
    @OneToMany(mappedBy = "ubicacion",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Equipo> listaEquipos = new ArrayList<>();

    // (1:M) INCIDENCIAS
    @OneToMany(mappedBy = "ubicacion",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Incidencia> listaIncidencias = new ArrayList<>();


    // HELPERS ---------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void addEquipo (Equipo e) {
        e.setUbicacion(this);
        listaEquipos.add(e);
    }

    public void removeEquipo (Equipo e) {
        listaEquipos.remove(e);
        e.setUbicacion(null);
    }

    public void addIncidencia (Incidencia i) {
        i.setUbicacion(this);
        listaIncidencias.add(i);
    }

    public void removeIncidencia (Incidencia i) {
        listaIncidencias.remove(i);
        i.setUbicacion(null);
    }


    // EQUALS & HASH CODE ----------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return getId() != null && Objects.equals(getId(), ubicacion.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
