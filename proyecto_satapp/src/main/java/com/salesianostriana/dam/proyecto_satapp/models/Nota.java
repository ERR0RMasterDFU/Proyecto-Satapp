package com.salesianostriana.dam.proyecto_satapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Nota {

    @Id // COMPOSICIÓN - NO TIENE ID
    private LocalDate fecha;
    private String contenido;
    //private

}
