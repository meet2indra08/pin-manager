package com.indra.pin.dal.repository;

import com.indra.pin.dal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByMsisdn(String msisdn);

}
