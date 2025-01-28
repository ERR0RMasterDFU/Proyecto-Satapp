package com.salesianostriana.dam.proyecto_satapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@Entity
public class Tecnico extends Usuario {

    // ASOCIACIONES ----------------------------------------------------------------------------------------------------------------------------------------------------------

    // (N:M) INCIDENCIA
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "gestiona",
            joinColumns = @JoinColumn(name="tecnico_id"),
            inverseJoinColumns = @JoinColumn(name="incidencia_id")
    )
    @Builder.Default
    @ToString.Exclude
    private Set<Incidencia> listaIncidenciasTecnico = new HashSet<>();


    // HELPERS ---------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void addIncidencia (Incidencia i) {
        this.listaIncidenciasTecnico.add(i);
        i.getListaTecnicos().add(this);
    }

    public void removeIncidencia (Incidencia i) {
        i.getListaTecnicos().remove(this);
        this.listaIncidenciasTecnico.remove(i);
    }


    // EQUALS & HASH CODE ----------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Tecnico tecnico = (Tecnico) o;
        return getId() != null && Objects.equals(getId(), tecnico.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
