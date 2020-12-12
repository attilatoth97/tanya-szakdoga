package hu.szakdolgozat.pm.config;

import hu.szakdolgozat.pm.web.exception.ProjectManagerValidationException;
import hu.szakdolgozat.pm.web.exception.ResourceNotFoundException;
import lombok.Data;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionTranslator  extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(ResourceNotFoundException ex) {
        return buildResponseEntity(new ApiError(ex.getMessage()), NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        return buildResponseEntity(new ApiError("Validációs hiba történt"), BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ApiError("Validációs hiba történt"), BAD_REQUEST);
    }

    @ExceptionHandler(ProjectManagerValidationException.class)
    protected ResponseEntity<Object> handleProjectManagerValidationException(ProjectManagerValidationException ex) {
        return buildResponseEntity(new ApiError(ex.getMessage()), BAD_REQUEST);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError, HttpStatus responseStatus) {
        return new ResponseEntity<>(apiError, responseStatus);
    }

    @Data
    public static class ApiError {

        private final String message;

        public ApiError(String message) {
            this.message = message;
        }
    }
}