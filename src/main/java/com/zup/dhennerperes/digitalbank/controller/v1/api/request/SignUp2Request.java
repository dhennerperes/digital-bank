package com.zup.dhennerperes.digitalbank.controller.v1.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    public void convertToEntity() {

    }

}
