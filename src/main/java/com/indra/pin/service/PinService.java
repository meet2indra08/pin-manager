package com.indra.pin.service;


import com.indra.pin.model.GeneratePinModel;
import com.indra.pin.model.PinResponse;
import com.indra.pin.model.ValidatePinModel;
import com.indra.pin.model.ValidatePinResponse;

public interface PinService {

    /**
     * Generates a Pin for the provided MSISDN.
     *
     * @param request request with MSISDN
     * @return Pin response
     */
    PinResponse generatePin(GeneratePinModel request);

    ValidatePinResponse validatePIN(ValidatePinModel request);



}
