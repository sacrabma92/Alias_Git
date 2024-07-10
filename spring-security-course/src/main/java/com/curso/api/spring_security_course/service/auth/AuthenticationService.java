package com.curso.api.spring_security_course.service.auth;

import com.curso.api.spring_security_course.dto.RegisterUser;
import com.curso.api.spring_security_course.dto.SaveUser;
import com.curso.api.spring_security_course.persistence.entity.User;
import com.curso.api.spring_security_course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    public RegisterUser registerOneCustomer(SaveUser newUser) {
        // Guardamos en BD el nuevo Cliente
        User user = userService.registerOneCustomer(newUser);

        // Este es el DTO que le vamos a devolver al cliente
        RegisterUser userDto = new RegisterUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());

        // Generar el token y lo añadimos al userDto
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userDto.setJwt(jwt);

        // Devolvemos tod el user con token creado
        return userDto;
    }

    private Map<String, Objects> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }
}
