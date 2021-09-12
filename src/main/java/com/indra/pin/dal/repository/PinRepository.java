package com.indra.pin.dal.repository;

import com.indra.pin.dal.entity.PinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PinRepository extends JpaRepository<PinEntity, UUID> {

    List<PinEntity> findByMsisdn(String msisdn);

}
