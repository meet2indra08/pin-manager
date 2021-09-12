package com.indra.pin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PinLimitExceedException extends RuntimeException {

    public PinLimitExceedException(String msg) {
        super(msg);
    }
}
