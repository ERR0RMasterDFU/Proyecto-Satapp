package com.salesianostriana.dam.proyecto_satapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String username;
    private String password;
    private String email;
    private String role;


    // ASOCIACIONES ----------------------------------------------------------------------------------------------------------------------------------------------------------

    // (1:M) INCIDENCIA
    @OneToMany(mappedBy = "usuario",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Incidencia> listaIncidencias = new ArrayList<>();


    // HELPERS ---------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void addIncidencia (Incidencia i) {
        i.setUsuario(this);
        listaIncidencias.add(i);
    }

    public void removeIncidencia (Incidencia i) {
        listaIncidencias.remove(i);
        i.setUsuario(null);
    }


    // EQUALS & HASH CODE ----------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Usuario usuario = (Usuario) o;
        return getId() != null && Objects.equals(getId(), usuario.getId());
    }

    @Override
    public int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
