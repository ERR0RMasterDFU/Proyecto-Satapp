package com.salesianostriana.dam.proyecto_satapp.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleUbicacionNotFound(EntityNotFoundException ex) {
        ProblemDetail result = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        result.setTitle("Entidad no encontrada.");
        //result.setType(URI.create("https://www.salesianos-triana.edu/errors/entity-not-found"));
        //result.setProperty("author", "David");

        return result;

    }
}
