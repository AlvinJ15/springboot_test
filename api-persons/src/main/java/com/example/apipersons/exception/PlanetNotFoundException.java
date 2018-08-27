package com.example.apipersons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class PlanetNotFoundException extends RuntimeException {
    private String planet;

    public PlanetNotFoundException( String planet) {
        super("El nombre de planeta ingresado no existe");
        this.planet = planet;
    }

    public String getPlanet() {
        return planet;
    }
}