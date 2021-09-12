package com.indra.pin.model;

import lombok.Data;

import java.time.Instant;

@Data
public class PinResponse {

    private String msisdn;

    private String pin;

    private Instant Created;

    private String status;

}
