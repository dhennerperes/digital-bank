package com.zup.dhennerperes.digitalbank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class Audit implements Serializable {

    private static final long serialVersionUID = -1323536687106645128L;

    @Column(name = "date_created", updatable = false)
    private LocalDateTime dateCreated;

    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;

    public Audit() {
        super();
    }

}
