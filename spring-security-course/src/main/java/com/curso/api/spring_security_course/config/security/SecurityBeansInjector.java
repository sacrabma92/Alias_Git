package com.curso.api.spring_security_course.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class SecurityBeansInjector {

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
   }

   // Vamos a crear nuestra estrategia de autenticación
   @Bean
   public AuthenticationProvider authenticationProvider(){
      DaoAuthenticationProvider authenticationStrategy = new DaoAuthenticationProvider();
      authenticationStrategy.setPasswordEncoder(null);
      authenticationStrategy.setPasswordEncoder(null);
      return  authenticationStrategy;
   }
}
