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
        result.setProperty("author", "David Falla Urtiaga y Daniel Martínez León");
        return result;
    }

    //CUANDO SE INTENTA ELIMINAR UNA UBICACIÓN CON EQUIPOS E INCIDENCIAS ASOCIADAS
    @ExceptionHandler(UbicacionEnUsoException.class)
    public ProblemDetail handleUbicacionEnUso(UbicacionEnUsoException ex) {
        ProblemDetail result = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        result.setTitle("La Ubicación NO se puede eliminar.");
        result.setProperty("author", "David Falla Urtiaga y Daniel Martínez León");
        return result;
    }

    /*CUANDO SE INTENTA ELIMINAR UNA UBICACIÓN CON EQUIPOS E INCIDENCIAS ASOCIADAS
    @ExceptionHandler(UbicacionEnUsoException.class)
    public ResponseEntity<String> handleUbicacionEnUso(UbicacionEnUsoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }*/

}
