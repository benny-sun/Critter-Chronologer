package com.udacity.jdnd.course3.critter.pet.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PetNotFoundException extends RuntimeException{

    public PetNotFoundException() {
    }

    public PetNotFoundException(long id) {
        super("Pet with ID " + id + " not found.");
    }

}
