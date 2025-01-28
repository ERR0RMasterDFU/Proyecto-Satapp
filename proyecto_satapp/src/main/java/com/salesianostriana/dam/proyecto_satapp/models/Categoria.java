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
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;


    // ASOCIACIONES ----------------------------------------------------------------------------------------------------------------------------------------------------------

    // (1:M) CATEGORIA
    @OneToMany(mappedBy = "categoriaPadre",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Categoria> listaSubCategorias = new ArrayList<>();

    // (M:1) CATEGORIA
    @ManyToOne
    @JoinColumn(name="categoriaPadre_categoria_id", foreignKey = @ForeignKey(name = "fk_categoriaPadre_categoria"))
    private Categoria categoriaPadre;

    // (1:M) INCIDENCIA
    @OneToMany(mappedBy = "categoria",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Incidencia> listaIncidencias = new ArrayList<>();


    // HELPERS ---------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void addIncidencia (Incidencia i) {
        i.setCategoria(this);
        listaIncidencias.add(i);
    }

    public void removeIncidencia (Incidencia i) {
        listaIncidencias.remove(i);
        i.setCategoria(null);
    }

    public void addCategoria (Categoria c) {
        c.setCategoriaPadre(this);
        listaSubCategorias.add(c);
    }

    public void removeProducto (Categoria c) {
        listaSubCategorias.remove(c);
        c.setCategoriaPadre(null);
    }


    // EQUALS & HASH CODE ----------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Categoria categoria = (Categoria) o;
        return getId() != null && Objects.equals(getId(), categoria.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
