package com.indra.pin.dal.entity;



import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "pin")
public class PinEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uid;

    private String msisdn;

    private String pin;

    private Instant created;

    private Boolean status;

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
