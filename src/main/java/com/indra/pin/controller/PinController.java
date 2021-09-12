package com.indra.pin.controller;

import com.indra.pin.model.GeneratePinModel;
import com.indra.pin.model.PinResponse;
import com.indra.pin.model.ValidatePinModel;
import com.indra.pin.model.ValidatePinResponse;
import com.indra.pin.service.PinService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Provide Services for PIN Management.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/pin")
public class PinController {

    private final PinService pinService;

    /**
     *
     * @param request
     * @return
     */

    @PostMapping(path = "generate")
    public PinResponse generate(@RequestBody @Valid GeneratePinModel request) {
        return pinService.generatePin(request);
    }

    /**
     *
     * @param request
     * @return
     */
    @PostMapping(path = "validate")
    public ValidatePinResponse validatePin(@RequestBody @Valid ValidatePinModel request) {
        return pinService.validatePIN(request);
    }

}
