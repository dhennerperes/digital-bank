package com.zup.dhennerperes.digitalbank.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Audit implements Serializable {

    private static final long serialVersionUID = -1323536687106645128L;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

}
