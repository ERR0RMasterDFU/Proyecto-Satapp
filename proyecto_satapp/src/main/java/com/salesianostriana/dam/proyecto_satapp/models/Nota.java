package com.salesianostriana.dam.proyecto_satapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime fecha;
    private String contenido;

    @Id
    private String autor;


}
