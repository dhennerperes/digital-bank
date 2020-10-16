package com.zup.dhennerperes.digitalbank.service.impl;

import com.zup.dhennerperes.digitalbank.model.PersonaAddress;
import com.zup.dhennerperes.digitalbank.model.SignUpStep;
import com.zup.dhennerperes.digitalbank.repository.PersonaAddressRepository;
import com.zup.dhennerperes.digitalbank.service.PersonaAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaAddressServiceImpl implements PersonaAddressService {

    private final PersonaAddressRepository personaAddressRepository;

    @Autowired
    public PersonaAddressServiceImpl(PersonaAddressRepository personaAddressRepository) {
        this.personaAddressRepository = personaAddressRepository;
    }

    @Override
    public PersonaAddress save2(PersonaAddress personaAddress, SignUpStep signUpStep) {
        personaAddress.setPersona(signUpStep.getPersona());
        PersonaAddress personaAddressSaved = this.personaAddressRepository.save(personaAddress);
        personaAddressSaved.setCode(signUpStep.getCode());
        return personaAddressSaved;
    }

}
