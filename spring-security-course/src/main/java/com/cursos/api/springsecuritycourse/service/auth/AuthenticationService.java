package com.cursos.api.springsecuritycourse.service.auth;

import com.cursos.api.springsecuritycourse.dto.RegisteredUser;
import com.cursos.api.springsecuritycourse.dto.SaveUser;
import com.cursos.api.springsecuritycourse.dto.auth.AuthenticationRequest;
import com.cursos.api.springsecuritycourse.dto.auth.AuthenticationResponse;
import com.cursos.api.springsecuritycourse.persistence.entity.User;
import com.cursos.api.springsecuritycourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

    // Traemos el metodo authenticate
    @Autowired
    private AuthenticationManager authenticationManager;

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

    public AuthenticationResponse login(AuthenticationRequest authRequest){
        // Creo el objeto para el login
        Authentication authentication = new UsernamePasswordAuthenticationToken(
          authRequest.getUsername(), authRequest.getPassword()
        );

        // Hago proceso de login, pasandolo al authentication
        authenticationManager.authenticate(authentication);

        // Creo los detalles del objeto
        UserDetails user = userService.findOneByUsername(authRequest.getUsername()).get();

        // Genero el JWT
        String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));

        // Creo la respuesta
        AuthenticationResponse authRsp = new AuthenticationResponse();
        authRsp.setJwt(jwt);
        return authRsp;
    }

    // Metodo que traera los extraclaims y validara el token que su estructura
    // Sea la correcta para nuestro servidor.
    public boolean validateToken(String jwt) {
        try{
            jwtService.extractUsername(jwt);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}