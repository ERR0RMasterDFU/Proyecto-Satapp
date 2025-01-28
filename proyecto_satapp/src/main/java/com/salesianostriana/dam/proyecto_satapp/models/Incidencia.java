package com.salesianostriana.dam.proyecto_satapp.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Incidencia {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate fecha;
    private String titulo;
    private String descripcion;
    private Estado estado;
    private boolean urgencia;


    // ASOCIACIONES ----------------------------------------------------------------------------------------------------------------------------------------------------------

    // (M:1) CATEGORIA
    @ManyToOne
    @JoinColumn(name="categoria_id", foreignKey = @ForeignKey(name = "fk_incidencia_categoria"))
    private Categoria categoria;

    // (M:1) EQUIPO
    @ManyToOne
    @JoinColumn(name="equipo_id", foreignKey = @ForeignKey(name = "fk_incidencia_equipo"))
    private Equipo equipo;

    // (M:1) UBICACION
    @ManyToOne
    @JoinColumn(name="ubicacion_id", foreignKey = @ForeignKey(name = "fk_incidencia_ubicacion"))
    private Ubicacion ubicacion;

    // (N:M) TECNICO
    @ManyToMany(mappedBy = "listaIncidencias", fetch = FetchType.EAGER)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Tecnico> listaTecnicos = new HashSet<>();

    // (1:M) NOTA
    @OneToMany(mappedBy = "incidencia",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Nota> listaNotas = new ArrayList<>();


    // HELPERS ---------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void addNota (Nota n) {
        n.setIncidencia(this);
        listaNotas.add(n);
    }

    public void removeNota (Nota n) {
        listaNotas.remove(n);
        //n.setIncidencia(null); ELIMINAMOS ESTA LÍNEA PARA EVITAR OTROS FALLOS
    }


    // EQUALS & HASH CODE ----------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Incidencia that = (Incidencia) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
