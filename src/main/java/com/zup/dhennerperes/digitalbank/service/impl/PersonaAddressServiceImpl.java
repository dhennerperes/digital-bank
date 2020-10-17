package com.zup.dhennerperes.digitalbank.service.impl;

import com.zup.dhennerperes.digitalbank.exception.ApplicationException;
import com.zup.dhennerperes.digitalbank.exception.EntityType;
import com.zup.dhennerperes.digitalbank.exception.ExceptionType;
import com.zup.dhennerperes.digitalbank.model.PersonaAddress;
import com.zup.dhennerperes.digitalbank.model.SignUpStep;
import com.zup.dhennerperes.digitalbank.repository.PersonaAddressRepository;
import com.zup.dhennerperes.digitalbank.service.PersonaAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public PersonaAddress findByPersonaId(Long personaId) {
        Optional<PersonaAddress> personaAddress = this.personaAddressRepository.findByPersonaId(personaId);
        if (personaAddress.isPresent()) {
            return personaAddress.get();
        }
        throw exception(EntityType.PERSONA_ADDRESS, ExceptionType.ENTITY_NOT_FOUND, personaId.toString());
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ApplicationException.throwException(entityType, exceptionType, args);
    }

}
