package com.zup.dhennerperes.digitalbank.security;

import com.zup.dhennerperes.digitalbank.controller.v1.api.request.SignUp1Request;
import com.zup.dhennerperes.digitalbank.controller.v1.api.request.SignUp2Request;
import com.zup.dhennerperes.digitalbank.dto.response.Response;
import com.zup.dhennerperes.digitalbank.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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
        //TODO: save persona
        httpResponse.addHeader("Location", "api/v1/authentication/signup/2");
        return Response.ok().setData(personaToBeAdded);
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
                "state", signUp2Request.getState()));
        signUp2Request.convertToEntity();
        //TODO: save persona
        httpResponse.addHeader("Location", "api/v1/authentication/signup/3");
        return Response.ok();
    }

    @PostMapping(path = "/signup/3", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<?> signUp3(@RequestParam("file") MultipartFile cpf_file, HttpServletResponse httpResponse) {
        logger.info(getOkLoggingMessage(
                "AuthenticationController", "signUp3",
                "cpf_file", cpf_file.getName()));
        //TODO: save cpf_file
        httpResponse.addHeader("Location", "api/v1/authentication/signup/4");
        return Response.ok();
    }

}
