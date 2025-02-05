package com.salesianostriana.dam.proyecto_satapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(description = "El proyecto busca desarrollar una herramienta de gestión de incidencias tecnológicas en un colegio para registrar, organizar, priorizar y dar seguimiento a los problemas, mejorando la comunicación y optimizando la resolución de fallos.",
        version = "0.1",
        contact = @Contact(
                email = "martinez.ledan23@triana.salesianos.edu\n," +
                        "falla.urdav24@triana.salesianos.edu",
                name = "Daniel Martínez León" + "\nDavid Falla Urtiaga"),
        license = @License(
                name = "Libre uso"),
        title = "Proyecto Satapp")
)
public class ProyectoSatappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoSatappApplication.class, args);
    }

}
