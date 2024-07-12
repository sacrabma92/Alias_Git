package com.curso.api.spring_security_course.service.auth;

import com.curso.api.spring_security_course.dto.RegisterUser;
import com.curso.api.spring_security_course.dto.SaveUser;
import com.curso.api.spring_security_course.dto.auth.AuthenticationRequest;
import com.curso.api.spring_security_course.dto.auth.AuthenticationResponse;
import com.curso.api.spring_security_course.persistence.entity.User;
import com.curso.api.spring_security_course.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    // Payload del JWT
    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationRequest authRequest) {
        // Creo el objeto para el login
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword()
        );

        // Hago proceso de login, pasandolo al authenticationManager
        authenticationManager.authenticate(authentication);

        // Creo los detalles del objeto
        UserDetails user = userService.findOneByUsername(authRequest.getUsername()).get();

        // Genero el JWT
        String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));

        // Creo la Respuesta
        AuthenticationResponse authRsp = new AuthenticationResponse();
        authRsp.setJwt(jwt);
        return authRsp;
    }

    // Validar el token. Valida el json, su firma de expiración.
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
