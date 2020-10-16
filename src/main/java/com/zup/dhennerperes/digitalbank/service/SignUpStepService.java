package com.zup.dhennerperes.digitalbank.service;

import com.zup.dhennerperes.digitalbank.model.SignUpStep;

import java.util.Optional;

public interface SignUpStepService {

    SignUpStep save(SignUpStep signUpStep);

    SignUpStep save1(SignUpStep signUpStep);

    Optional<SignUpStep> findByPersonaId(Long personaId);

    SignUpStep findByCodeAndStep(String code, Integer step);

}
