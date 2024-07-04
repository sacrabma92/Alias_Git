package com.springdatajpa.exceptions;

import org.springframework.http.HttpStatus;

public class EcommerceAPIException extends RuntimeException {
   // Muestra el estado del Error y el Mensaje que envia.
   private HttpStatus status;
   private String message;

   public EcommerceAPIException(HttpStatus status, String message) {
      this.status = status;
      this.message = message;
   }

   public EcommerceAPIException(String message, HttpStatus status, String message1) {
      super(message);
      this.status = status;
      this.message = message1;
   }

   public HttpStatus getStatus() {
      return status;
   }

   @Override
   public String getMessage() {
      return message;
   }
}
