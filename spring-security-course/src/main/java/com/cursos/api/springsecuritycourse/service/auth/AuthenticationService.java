package com.cursos.api.springsecuritycourse.service.auth;

import com.cursos.api.springsecuritycourse.dto.RegisteredUser;
import com.cursos.api.springsecuritycourse.dto.SaveUser;
import com.cursos.api.springsecuritycourse.persistence.entity.User;
import com.cursos.api.springsecuritycourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    // Traemos el metodo de registerOneCustomer
    @Autowired
    private UserService userService;

    // Traemos el metodo generateToken
    @Autowired
    private JwtService jwtService;

    // Metodo para registrar un cliente.
    public RegisteredUser registerOneCustomer(SaveUser newUser) {
        // Guardamos en la BD un nuevo Cliente
        User user = userService.registerOneCustomer(newUser);

        // Este es el DTO que le vamos a devolver al cliente
        RegisteredUser userDto = new RegisteredUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());

        // Generar el token y lo añadimos al userDto
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userDto.setJwt(jwt);

        // Devolvemos todos el user con el token creado
        return userDto;
    }

    // Generamos el extraClaims, guardamos el name, role, authorities.
    private Map<String, Object> generateExtraClaims(User user){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }
}