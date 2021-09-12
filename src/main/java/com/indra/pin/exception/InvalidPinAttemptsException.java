package com.indra.pin.exception;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class InvalidPinAttemptsException extends RuntimeException {
    public InvalidPinAttemptsException(String msg) {
        super(msg);
    }
}
