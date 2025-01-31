package com.salesianostriana.dam.proyecto_satapp.error;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFound(EntityNotFoundException ex) {
        ProblemDetail result = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        result.setTitle("Entidad no encontrada.");
        //result.setType(URI.create("https://www.salesianos-triana.edu/errors/entity-not-found"));
        //result.setProperty("author", "David");
        return result;
    }

    //CUANDO SE INTENTA ELIMINAR UNA UBICACIÓN CON EQUIPOS E INCIDENCIAS ASOCIADAS
    @ExceptionHandler(UbicacionEnUsoException.class)
    public ResponseEntity<String> handleUbicacionEnUso(UbicacionEnUsoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
