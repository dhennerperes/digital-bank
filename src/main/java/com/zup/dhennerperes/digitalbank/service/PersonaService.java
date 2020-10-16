package com.zup.dhennerperes.digitalbank.service;

import com.zup.dhennerperes.digitalbank.model.Persona;

import java.util.Optional;

public interface PersonaService {

    Persona save1(Persona persona, SignUpStepService signUpStepService);

    Optional<Persona> findByCpf(String cpf);

}
