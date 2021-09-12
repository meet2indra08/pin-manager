package com.indra.pin.model;

import static com.indra.pin.constant.ValidationMessages.REG_EMPTY_OR_TEN_DIGIT;
import static com.indra.pin.constant.ValidationMessages.MSG_VALIDATE_CELL_NUMBER;
import static com.indra.pin.constant.ValidationMessages.MSISDN_INVALID;
import lombok.Data;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Data
public class GeneratePinModel {

    @NotEmpty(message = MSISDN_INVALID)
    @Pattern(regexp = REG_EMPTY_OR_TEN_DIGIT, message = MSG_VALIDATE_CELL_NUMBER)
    private String msisdn;
}
