package com.zup.dhennerperes.digitalbank.exception;

import com.zup.dhennerperes.digitalbank.config.PropertiesConfig;
import com.zup.dhennerperes.digitalbank.dto.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static PropertiesConfig propertiesConfig;

    @Autowired
    public CustomizedResponseEntityExceptionHandler(PropertiesConfig propertiesConfig) {
        CustomizedResponseEntityExceptionHandler.propertiesConfig = propertiesConfig;
    }

    @ExceptionHandler(ApplicationException.EntityNotFoundException.class)
    public final ResponseEntity<?> handleNotFountExceptions(Exception ex, WebRequest request) {
        Response<?> response = Response.notFound();
        response.addErrorMsgToResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApplicationException.DuplicateEntityException.class)
    public final ResponseEntity<?> handleNotFountExceptions1(Exception ex, WebRequest request) {
        Response<?> response = Response.duplicateEntity();
        response.addErrorMsgToResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ApplicationException.NotPermissionException.class)
    public final ResponseEntity<?> handleNotFountExceptions2(Exception ex, WebRequest request) {
        Response<?> response = Response.notPermissionEntity();
        response.addErrorMsgToResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ApplicationException.EntityInvalidException.class)
    public final ResponseEntity<?> handleNotFountExceptions3(Exception ex, WebRequest request) {
        Response<?> response = Response.notInvalidEntity();
        response.addErrorMsgToResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApplicationException.ForbiddenException.class)
    public final ResponseEntity<?> handleNotFountExceptions4(Exception ex, WebRequest request) {
        Response<?> response = Response.forbidden();
        response.addErrorMsgToResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Response<?> response = Response.badRequest();
        List<String> errorsCode = new ArrayList<>();

        for (FieldError fields : fieldErrors) {
            errorsCode.add(fields.getDefaultMessage());
        }
        response.addErrorMsgToResponse(errorsCode);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
