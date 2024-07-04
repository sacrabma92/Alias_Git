package com.springdatajpa.exceptions;

import com.springdatajpa.Dto.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

   // Manejar Excepcion especifica.

   // Esta exceptio se lanza cuando un recurso no se encuentra. Lanza un 404 Not_Found
   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                       WebRequest webRequest){
      ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
              webRequest.getDescription(false));
      return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
   }

   // Este método captura las excepciones EcommerceAPIException que ocurren en la aplicación, crea un
   // objeto detallado con la información del error y devuelve esta información al cliente con un estado
   // HTTP 400 (solicitud incorrecta).
   @ExceptionHandler(EcommerceAPIException.class)
   public ResponseEntity<ErrorDetails> handleEcommerceAPIException(EcommerceAPIException exception,
                                                                       WebRequest webRequest){
      ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
              webRequest.getDescription(false));
      return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
   }

   // Excepciones Globales.
   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,
                                                                   WebRequest webRequest){
      ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
              webRequest.getDescription(false));
      return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
   }

   // Lanza la excepcion cuando hay Notations en las entidades
   // Por ejemplo: @Size, NotEmpty

   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                 HttpHeaders headers,
                                                                 HttpStatusCode status,
                                                                 WebRequest request) {
      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error) ->{
         String fieldName = ((FieldError)error).getField();
         String message = error.getDefaultMessage();
         errors.put(fieldName, message);
      });

      return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
   }

}
