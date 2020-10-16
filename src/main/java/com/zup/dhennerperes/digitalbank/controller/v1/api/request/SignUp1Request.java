package com.zup.dhennerperes.digitalbank.controller.v1.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zup.dhennerperes.digitalbank.model.Persona;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUp1Request {

    @NotEmpty(message = "signup1.name.empty")
    private String name;

    @NotEmpty(message = "signup1.last_name.empty")
    private String last_name;

    @NotEmpty(message = "signup.email.empty")//TODO: validar a necessidade
    @Email(message = "signup1.email.invalid")
    private String email;

    @NotNull(message = "signup1.birth_date.empty")
    @Past(message = "signup1.birth_date.invalid")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //TODO: validar maioridade
    private LocalDate birth_date;

    @NotEmpty(message = "signup1.cpf.empty")//TODO: validar a necessidade
    @CPF(message = "signup1.cpf.invalid")
    private String cpf;

    public Persona convertToEntity() {
        Persona persona = new Persona();
        persona.setName(this.name);
        persona.setLastName(this.last_name);
        persona.setEmail(this.email);
        persona.setBirthDate(this.birth_date);
        persona.setCpf(this.cpf);
        persona.setDateCreated(LocalDateTime.now());
        return persona;
    }

}
