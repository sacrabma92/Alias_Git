package com.curso.api.spring_security_course.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    // Cadena para filtro de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        SecurityFilterChain filterChain = http
                // No utilizara el tipo de token
                .csrf( csrfConfig -> csrfConfig.disable() )
                // Sesion sin estado
                .sessionManagement( sessMagConfig -> sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configuraos con la estrategia
                .authenticationProvider( authenticationProvider )
                // Configración de las rutas
                .authorizeHttpRequests( authReqConfig -> {

                    // unicas rutas que son publicas y cualquiera puede accedder
                    authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
                    authReqConfig.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();

                    // Todas las demas rutas estaran bloqueadas, necesitaran autenticación
                    authReqConfig.anyRequest().authenticated();

                })
                .build();
        return filterChain;
    }
}
