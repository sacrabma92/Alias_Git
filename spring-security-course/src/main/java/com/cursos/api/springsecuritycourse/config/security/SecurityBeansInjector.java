package com.cursos.api.springsecuritycourse.config.security;

import com.cursos.api.springsecuritycourse.exception.ObjectNotFoundException;
import com.cursos.api.springsecuritycourse.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeansInjector {

   @Autowired
   private UserRepository userRepository;

   @Bean
   public AuthenticationManager authenticationManager(
           AuthenticationConfiguration authenticationConfiguration)
           throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
   }

   // Vamos a crear nuestra estrategia de autenticación
   @Bean
   public AuthenticationProvider authenticationProvider(){
      // Creamos una instancia de DaoAuthenticationProvider
      DaoAuthenticationProvider authenticationStrategy = new DaoAuthenticationProvider();
      // setPasswordEncoder compara el password y encripta la constraseña
      authenticationStrategy.setPasswordEncoder( passwordEncoder() );
      // setUserDetailsService envia a la BD user y trae el payload
      authenticationStrategy.setUserDetailsService( null );

      // Retornamos la respuesta
      return authenticationStrategy;
   }

   // Encriptar el password
   @Bean
   public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }

   // Carga los detalles del usuario basados en el username
   // proporcionado utilizando un repositorio de Spring Data JPA
   @Bean
   public UserDetailsService userDetailsService(){
      return (username) -> {
         return userRepository.findByUserName(username)
                 .orElseThrow(()-> new ObjectNotFoundException("User not found with username " + username));
      };
   }

}
