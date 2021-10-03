package br.com.cm.workshop.apicrud.controllers.exceptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.cm.workshop.apicrud.services.exceptions.StatusException;

import org.springframework.validation.FieldError;


@ControllerAdvice
public class ControllersExceptionsHandler {

    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<StandardError> unsupported( UnsupportedOperationException e, HttpServletRequest request) {
        String error = "Regra de Negocio não atendida !";
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<StandardError> EntityNotFound(EntityNotFoundException e, HttpServletRequest request) {
		String error = "Entidade Não Encontrada!";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<StandardError> HandleValidationsConstraintViolationExceptions(ConstraintViolationException ex,HttpServletRequest request) {
        String error = "Dados Inseridos Invalidos!";
		HttpStatus status = HttpStatus.BAD_REQUEST;
        String Message = ex.getMessage().substring(ex.getMessage().indexOf("='")+2, ex.getMessage().indexOf("',"));
        StandardError err = new StandardError(Instant.now(), status.value(), error, Message, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((err) -> {
            String fieldName = ((FieldError) err).getField();
            String errorMessage = err.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(status).body(errors);
    }
   
    @ExceptionHandler(StatusException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Map<String, String>> StatusExceptions(StatusException ex) {
		
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        Map<String, String> errors = new HashMap<>();
        errors.put("mensagem", ex.getMessage());
        return ResponseEntity.status(status).body(errors);
    }

}
