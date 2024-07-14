package com.cursos.api.springsecuritycourse.config.security;

import com.cursos.api.springsecuritycourse.config.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // Cadena para filtro de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        SecurityFilterChain filterChain = http
            // No utilizara el tipo de token
            .csrf( csrfConfig -> csrfConfig.disable() )
            // Aplicación sin estado
            .sessionManagement( sessMagConfig -> sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            //  Añadimos un filtro antes de ejecutar UsernamePasswordAuthenticationFilter.class
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            // Configuramos la estraetegia
            .authenticationProvider( authenticationProvider )
            // Configuracion de las rutas
            .authorizeHttpRequests( authReqConfig -> {
                // Unicas rutas que son publicas y cualquiera pueden acceder
                authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
                authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();

                // Todas las demas rutas estaran bloqueadas, necesitaran autenticación
                authReqConfig.anyRequest().authenticated();
            })
                .build();
        return filterChain;
    }
}
