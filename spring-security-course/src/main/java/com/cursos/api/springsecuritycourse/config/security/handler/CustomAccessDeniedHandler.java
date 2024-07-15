package com.cursos.api.springsecuritycourse.config.security.handler;

import com.cursos.api.springsecuritycourse.dto.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ApiError error = new ApiError();
        error.setMessage("Acceso denegado. No tiene los permismos necesarios para acceder a esta funcion." +
                "Por favor, contactar al administrador si crees que esto es un error.");
        error.setBackedMessage(accessDeniedException.getLocalizedMessage());
        error.setTime(LocalDateTime.now());
        error.setHttpCode(403);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String apiErrorAsJson = objectMapper.writeValueAsString(error);

        response.getWriter().write(apiErrorAsJson);
    }
}
