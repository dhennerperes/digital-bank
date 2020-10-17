package com.zup.dhennerperes.digitalbank.controller.v1.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zup.dhennerperes.digitalbank.model.SignUpStep;
import com.zup.dhennerperes.digitalbank.service.SignUpStepService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUp3Request {

    @NotEmpty(message = "signup3.code.empty")
    private String code;

    public SignUpStep checkCodeAndUpdate(SignUpStepService signUpStepService) {
        SignUpStep signUpStep = signUpStepService.findByCodeAndStep(this.code, 2);
        signUpStep.setStep(3);
        signUpStep.setDateUpdated(LocalDateTime.now());
        return signUpStepService.save(signUpStep);
    }

}
