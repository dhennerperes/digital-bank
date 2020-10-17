package com.zup.dhennerperes.digitalbank.service;

import com.zup.dhennerperes.digitalbank.model.PersonaAddress;
import com.zup.dhennerperes.digitalbank.model.SignUpStep;

public interface PersonaAddressService {

    PersonaAddress save2(PersonaAddress personaAddress, SignUpStep signUpStep);

    PersonaAddress findByPersonaId(Long personaId);

}
