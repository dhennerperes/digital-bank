package com.zup.dhennerperes.digitalbank.service.impl;

import com.zup.dhennerperes.digitalbank.exception.ApplicationException;
import com.zup.dhennerperes.digitalbank.exception.EntityType;
import com.zup.dhennerperes.digitalbank.exception.ExceptionType;
import com.zup.dhennerperes.digitalbank.model.SignUpStep;
import com.zup.dhennerperes.digitalbank.repository.SignUpStepRepository;
import com.zup.dhennerperes.digitalbank.service.SignUpStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignUpStepServiceImpl implements SignUpStepService {

    private final SignUpStepRepository signUpStepRepository;

    @Autowired
    public SignUpStepServiceImpl(SignUpStepRepository signUpStepRepository) {
        this.signUpStepRepository = signUpStepRepository;
    }

    @Override
    public SignUpStep save(SignUpStep signUpStep) {
        return this.signUpStepRepository.save(signUpStep);
    }

    @Override
    public SignUpStep save1(SignUpStep signUpStep) {
        if (this.findByPersonaId(signUpStep.getPersona().getId()).isPresent()) {
            throw exception(EntityType.SIGN_UP_STEP, ExceptionType.DUPLICATE_ENTITY, signUpStep.getCode());
        }
        return this.signUpStepRepository.save(signUpStep);
    }

    @Override
    public Optional<SignUpStep> findByPersonaId(Long personaId) {
        return this.signUpStepRepository.findByPersonaId(personaId);
    }

    @Override
    public SignUpStep findByCodeAndStep(String code, Integer step) {
        Optional<SignUpStep> signUpStep = this.signUpStepRepository.findByCodeAndStep(code, step);
        if (signUpStep.isPresent()) {
            return signUpStep.get();
        }
        throw exception(EntityType.SIGN_UP_STEP, ExceptionType.ENTITY_NOT_FOUND, code);
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ApplicationException.throwException(entityType, exceptionType, args);
    }

}
