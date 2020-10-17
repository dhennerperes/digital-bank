package com.zup.dhennerperes.digitalbank.controller.v1.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zup.dhennerperes.digitalbank.model.SignUpStep;
import com.zup.dhennerperes.digitalbank.service.SignUpStepService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUp4Request {

    @NotEmpty(message = "signup4.code.empty")
    private String code;

    public SignUpStep checkCode(SignUpStepService signUpStepService) {
        return signUpStepService.findByCodeAndStep(this.code, 3);
    }

}
