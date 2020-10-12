package com.zup.dhennerperes.digitalbank.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Persona extends Audit{

    private static final long serialVersionUID = -4020106671035473812L;

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String cpf;

}
