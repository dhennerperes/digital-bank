package com.zup.dhennerperes.digitalbank.controller.v1.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zup.dhennerperes.digitalbank.model.PersonaAddress;
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
public class SignUp2Request {

    @NotEmpty(message = "signup.cpf.empty")
    //TODO: @CEP
    private String cep;

    @NotEmpty(message = "signup2.street.empty")
    private String street;

    @NotEmpty(message = "signup2.neighborhood.empty")
    private String neighborhood;

    @NotEmpty(message = "signup2.complement.empty")
    private String complement;

    @NotEmpty(message = "signup2.city.empty")
    private String city;

    @NotEmpty(message = "signup2.state.empty")
    private String state;

    @NotEmpty(message = "signup2.code.empty")
    private String code;

    public PersonaAddress convertToEntity() {
        PersonaAddress personaAddress = new PersonaAddress();
        personaAddress.setCep(this.cep);
        personaAddress.setStreet(this.street);
        personaAddress.setNeighborhood(this.neighborhood);
        personaAddress.setComplement(this.complement);
        personaAddress.setCity(this.city);
        personaAddress.setState(this.state);
        return personaAddress;
    }

    public SignUpStep checkCodeAndUpdate(SignUpStepService signUpStepService) {
        SignUpStep signUpStep = signUpStepService.findByCodeAndStep(this.code, 1);
        signUpStep.setStep(2);
        signUpStep.setDateUpdated(LocalDateTime.now());
        return signUpStepService.save(signUpStep);
    }

}
