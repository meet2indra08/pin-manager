package com.indra.pin.dal.entity;



import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "d_user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uid;

    private String msisdn;

    private Instant created;

    private String status;

    @PrePersist
    private void beforeAnyPersist() {
        created = Instant.now();
    }

    /**
     * Before any update.
     */
    @PreUpdate
    private void beforeAnyUpdate() {
        created = Instant.now();
    }


}
