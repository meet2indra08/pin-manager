package com.indra.pin.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.indra.pin.constant.ValidationMessages.*;

@Data
public class ValidatePinModel {
    @NotEmpty(message = MSISDN_INVALID)
    @Pattern(regexp = REG_EMPTY_OR_TEN_DIGIT, message = MSG_VALIDATE_CELL_NUMBER)
    private String msisdn;

    @NotEmpty(message = PIN_INVALID)
    private String pin;
}
