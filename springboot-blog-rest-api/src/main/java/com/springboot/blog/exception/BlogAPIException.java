
package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;
// Lanzamos esta excepción cada vez que escribimos alguna lógica de negocio o validamos algún parámetro de la petición
public class BlogAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public BlogAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
    
    
}
