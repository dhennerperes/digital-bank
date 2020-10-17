package com.zup.dhennerperes.digitalbank.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zup.dhennerperes.digitalbank.model.Persona;
import com.zup.dhennerperes.digitalbank.model.PersonaAddress;
import com.zup.dhennerperes.digitalbank.service.PersonaAddressService;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaFullDto implements Serializable {

    private static final long serialVersionUID = -2803563007133612597L;

    private String name;
    private String last_name;
    private String email;
    private LocalDate birth_date;
    private String cpf;
    private String cep;
    private String street;
    private String neighborhood;
    private String complement;
    private String city;
    private String state;

    public PersonaFullDto(Persona persona, PersonaAddressService personaAddressService) {
        this.name = persona.getName();
        this.last_name = persona.getLastName();
        this.email = persona.getEmail();
        this.birth_date = persona.getBirthDate();
        this.cpf = persona.getCpf();
        PersonaAddress personaAddress = personaAddressService.findByPersonaId(persona.getId());
        this.cep = personaAddress.getCep();
        this.street = personaAddress.getStreet();
        this.neighborhood = personaAddress.getNeighborhood();
        this.complement = personaAddress.getComplement();
        this.city = personaAddress.getCity();
        this.state = personaAddress.getState();
    }

}
