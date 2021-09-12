package com.indra.pin.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.indra.pin.constant.ValidationMessages.*;

@Data
public class ValidatePinResponse {
    private String msisdn;
    private String pin;
    private Enum PinStatus;
}
