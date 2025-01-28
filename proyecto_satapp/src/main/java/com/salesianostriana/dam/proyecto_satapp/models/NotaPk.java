package com.salesianostriana.dam.proyecto_satapp.models;

import lombok.*;
import java.io.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotaPk implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Incidencia incidencia;

}

