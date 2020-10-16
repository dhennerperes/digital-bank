package com.zup.dhennerperes.digitalbank.service.impl;

import com.zup.dhennerperes.digitalbank.exception.ApplicationException;
import com.zup.dhennerperes.digitalbank.exception.EntityType;
import com.zup.dhennerperes.digitalbank.exception.ExceptionType;
import com.zup.dhennerperes.digitalbank.model.Persona;
import com.zup.dhennerperes.digitalbank.model.SignUpStep;
import com.zup.dhennerperes.digitalbank.repository.PersonaRepository;
import com.zup.dhennerperes.digitalbank.service.PersonaService;
import com.zup.dhennerperes.digitalbank.service.SignUpStepService;
import com.zup.dhennerperes.digitalbank.util.CodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    private static final Logger logger = LoggerFactory.getLogger(PersonaServiceImpl.class);

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public Persona save1(Persona persona, SignUpStepService signUpStepService) {
        if (this.findByCpf(persona.getCpf()).isPresent()) {
            throw exception(EntityType.PERSONA, ExceptionType.DUPLICATE_ENTITY, persona.getCpf());
        }
        Persona personaSaved = this.personaRepository.save(persona);
        SignUpStep signUpStep = signUpStepService.save1(this.create(personaSaved));
        personaSaved.setCode(signUpStep.getCode());
        return personaSaved;
    }

    private SignUpStep create(Persona persona) {
        SignUpStep signUpStep = new SignUpStep();
        signUpStep.setCode(CodeUtil.getShortUUID());
        signUpStep.setStep(1);
        signUpStep.setPersona(persona);
        signUpStep.setDateCreated(LocalDateTime.now());
        return signUpStep;
    }

    @Override
    public Optional<Persona> findByCpf(String cpf) {
        return this.personaRepository.findByCpf(cpf);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ApplicationException.throwException(entityType, exceptionType, args);
    }

}
