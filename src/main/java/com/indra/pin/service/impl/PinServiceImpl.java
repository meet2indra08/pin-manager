package com.indra.pin.service.impl;

import com.indra.pin.dal.entity.PinEntity;
import com.indra.pin.dal.repository.UserRepository;
import com.indra.pin.exception.InvalidPinAttemptsException;
import com.indra.pin.exception.PinLimitExceedException;
import com.indra.pin.model.*;
import com.indra.pin.dal.repository.PinRepository;
import com.indra.pin.service.PinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Service
@Slf4j
public class PinServiceImpl implements PinService {

    @Autowired
    private PinRepository pinRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());

    public static Long nextId() {
        return currentTime.accumulateAndGet(System.currentTimeMillis(), (prev, next) -> next > prev ? next : prev + 1) % 10000000000L;
    }

    @Override
    @Transactional
    public PinResponse generatePin(GeneratePinModel request) {



        List<PinEntity> exitingPinList = pinRepository.findByMsisdn(request.getMsisdn());
        PinEntity pinEntity = new PinEntity();
        if (exitingPinList.size() >= 3) {
            throw new PinLimitExceedException("Maximum Number Of PIN already there with MSISDN " + request.getMsisdn());
        }
        String pin = String.valueOf(generatePin(request.getMsisdn()));
        pinEntity.setMsisdn(request.getMsisdn());
        pinEntity.setPin(pin);
        pinEntity.setStatus(false);
        pinRepository.save(pinEntity);
        redisTemplate.opsForValue().set(request.getMsisdn(), 0);
        return convertToResponse(pinEntity);
    }

    private PinResponse convertToResponse(PinEntity pinEntity) {
        PinResponse pinResponse = new PinResponse();
        pinResponse.setPin(pinEntity.getPin());
        pinResponse.setMsisdn(pinEntity.getMsisdn());
        pinResponse.setCreated(pinEntity.getCreated());
        pinResponse.setStatus("Created");
        return pinResponse;
    }

    /**
     * Generate PIN and Store Into Cache along with expiry
     *
     * @param key
     * @return
     */
    public int generatePin(String key) {
        int pin = (int) (Math.random() * 9000) + 1000;
        return pin;
    }


    @Override
    public ValidatePinResponse validatePIN(ValidatePinModel request) {

        String msisdn = request.getMsisdn();
        String pin = request.getPin();
        List<PinEntity> pinEntityList = pinRepository.findByMsisdn(msisdn);
        PinEntity pinEntity1 = pinEntityList.stream()
                .filter(pinEntity -> pin.equals(pinEntity.getPin()))
                .findAny()
                .orElse(null);
        System.out.println("Test");
        if (!ObjectUtils.isEmpty(pinEntity1)) {
            pinEntity1.setStatus(true);
            pinRepository.save(pinEntity1);
            redisTemplate.opsForValue().set(request.getMsisdn(), 0);
            return MarshalValidatePinResponse(pinEntity1);
        }else {
            int pinCount = Integer.valueOf(String.valueOf(redisTemplate.opsForValue().get(request.getMsisdn())));
            if(pinCount >= 3){
                log.info("Invalid Attempts reached to limit Deleting all PINS for MSISDN:"+msisdn);
                pinRepository.deleteAll(pinEntityList);
            }
            redisTemplate.opsForValue().set(request.getMsisdn(), Integer.valueOf(String.valueOf(redisTemplate.opsForValue().get(request.getMsisdn()))) + 1);
        }

        throw new InvalidPinAttemptsException("Invalid PIN attempts for MSISDN " + request.getMsisdn());
    }

    private ValidatePinResponse MarshalValidatePinResponse(PinEntity pinEntity1) {
        ValidatePinResponse validatePinResponse = new ValidatePinResponse();
        validatePinResponse.setMsisdn(pinEntity1.getMsisdn());
        validatePinResponse.setPin(pinEntity1.getPin());
        validatePinResponse.setPinStatus(PinStatus.VERIFIED);
        return validatePinResponse;
    }

    /**
     * Get PIN From cache
     *
     * @param key
     * @return
     */
    public int getPin(String key) {
        try {
            //      return otpCache.get(key);
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }

}
