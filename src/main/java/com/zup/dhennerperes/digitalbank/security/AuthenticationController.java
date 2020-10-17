package com.zup.dhennerperes.digitalbank.security;

import com.zup.dhennerperes.digitalbank.controller.v1.api.request.SignUp1Request;
import com.zup.dhennerperes.digitalbank.controller.v1.api.request.SignUp2Request;
import com.zup.dhennerperes.digitalbank.controller.v1.api.request.SignUp3Request;
import com.zup.dhennerperes.digitalbank.controller.v1.api.request.SignUp4Request;
import com.zup.dhennerperes.digitalbank.dto.response.PersonaFullDto;
import com.zup.dhennerperes.digitalbank.dto.response.Response;
import com.zup.dhennerperes.digitalbank.model.Persona;
import com.zup.dhennerperes.digitalbank.model.PersonaAddress;
import com.zup.dhennerperes.digitalbank.model.SignUpStep;
import com.zup.dhennerperes.digitalbank.service.PersonaAddressService;
import com.zup.dhennerperes.digitalbank.service.PersonaService;
import com.zup.dhennerperes.digitalbank.service.SignUpStepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static com.zup.dhennerperes.digitalbank.util.LoggingUtil.getOkLoggingMessage;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final PersonaService personaService;
    private final PersonaAddressService personaAddressService;
    private final SignUpStepService signUpStepService;

    @Autowired
    public AuthenticationController(
            PersonaService personaService,
            PersonaAddressService personaAddressService,
            SignUpStepService signUpStepService) {
        this.personaService = personaService;
        this.personaAddressService = personaAddressService;
        this.signUpStepService = signUpStepService;
    }

    @PostMapping(path = "/signup/1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<?> signUp1(@Valid @RequestBody SignUp1Request signUp1Request, HttpServletResponse httpResponse) {
        logger.info(getOkLoggingMessage(
                "AuthenticationController", "signUp1",
                "name", signUp1Request.getName(),
                "last_name", signUp1Request.getLast_name(),
                "email", signUp1Request.getEmail(),
                "birth_date", signUp1Request.getBirth_date().toString(),
                "cpf", signUp1Request.getCpf()));
        Persona personaToBeAdded = signUp1Request.convertToEntity();
        Persona personaSaved = this.personaService.save1(personaToBeAdded, this.signUpStepService);
        httpResponse.addHeader("Location", "api/v1/authentication/signup/2");
        httpResponse.addHeader("Code-Account", personaSaved.getCode());
        httpResponse.setStatus(201);
        return Response.ok();
    }

    @PostMapping(path = "/signup/2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<?> signUp2(@Valid @RequestBody SignUp2Request signUp2Request, HttpServletResponse httpResponse) {
        logger.info(getOkLoggingMessage(
                "AuthenticationController", "signUp2",
                "cep", signUp2Request.getCep(),
                "street", signUp2Request.getStreet(),
                "neighborhood", signUp2Request.getNeighborhood(),
                "complement", signUp2Request.getComplement(),
                "city", signUp2Request.getCity(),
                "state", signUp2Request.getState(),
                "code", signUp2Request.getCode()));
        SignUpStep signUpStep = signUp2Request.checkCodeAndUpdate(this.signUpStepService);
        PersonaAddress personaAddressToAdded = signUp2Request.convertToEntity();
        PersonaAddress personaAddressSaved = this.personaAddressService.save2(personaAddressToAdded, signUpStep);
        httpResponse.addHeader("Location", "api/v1/authentication/signup/3");
        httpResponse.addHeader("Code-Account", personaAddressSaved.getCode());
        httpResponse.setStatus(201);
        return Response.ok();
    }

    @PostMapping(path = "/signup/3", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<?> signUp3(@Valid @RequestBody SignUp3Request signUp3Request, @RequestParam(value = "file", required = false) MultipartFile cpf_file, HttpServletResponse httpResponse) {
        logger.info(getOkLoggingMessage(
                "AuthenticationController", "signUp3",
                "code", signUp3Request.getCode()));
        //TODO: save cpf_file
        SignUpStep signUpStep = signUp3Request.checkCodeAndUpdate(this.signUpStepService);
        httpResponse.addHeader("Location", "api/v1/authentication/signup/4");
        httpResponse.addHeader("Code-Account", signUpStep.getCode());
        httpResponse.setStatus(201);
        return Response.ok();
    }

    @GetMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<?> getAccountByCode(@Valid @RequestBody SignUp4Request signUp4Request, HttpServletResponse httpResponse) {
        logger.info(getOkLoggingMessage(
                "AuthenticationController", "getAccountByCode",
                "code", signUp4Request.getCode()));
        SignUpStep signUpStep = signUp4Request.checkCode(this.signUpStepService);
        httpResponse.addHeader("Code-Account", signUpStep.getCode());
        return Response.ok().setData(new PersonaFullDto(signUpStep.getPersona(), this.personaAddressService));
    }

}
