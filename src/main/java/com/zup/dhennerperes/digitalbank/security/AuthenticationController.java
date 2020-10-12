package com.zup.dhennerperes.digitalbank.security;

import com.zup.dhennerperes.digitalbank.controller.v1.api.request.SignUpRequest;
import com.zup.dhennerperes.digitalbank.dto.response.Response;
import com.zup.dhennerperes.digitalbank.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.zup.dhennerperes.digitalbank.util.LoggingUtil.getOkLoggingMessage;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        logger.info(getOkLoggingMessage(
                "AuthenticationController", "signUp",
                "name", signUpRequest.getName(),
                "last_name", signUpRequest.getLast_name(),
                "email", signUpRequest.getEmail(),
                "birth_date", signUpRequest.getBirth_date().toString(),
                "cpf", signUpRequest.getCpf()));
        Persona personaToBeAdded = signUpRequest.convertToEntity();
        //TODO: save persona
        return Response.ok().setData(personaToBeAdded);
    }
}
