package com.salesianostriana.dam.proyecto_satapp.models;

import lombok.*;

import java.io.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class NotaPk implements Serializable {

    private Incidencia incidencia;
    private LocalDateTime fecha;
    private String autor;

}
