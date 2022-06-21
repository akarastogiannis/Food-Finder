package com.athanasiosk.FoodFinder.Owner;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OwnerNotFoundException extends RuntimeException{

    OwnerNotFoundException(Long id) {
        super("Could not find the Owner with id " + id);
    }
}
