package com.curso.api.spring_security_course.service.impl;

import com.curso.api.spring_security_course.dto.SaveUser;
import com.curso.api.spring_security_course.exception.InvalidPasswordException;
import com.curso.api.spring_security_course.persistence.entity.User;
import com.curso.api.spring_security_course.persistence.repository.UserRepository;
import com.curso.api.spring_security_course.persistence.repository.util.Role;
import com.curso.api.spring_security_course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public class UserServiceImpl implements UserService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Override
   public User registerOneCustomer(SaveUser newUser) {

      // Validamos la contraseña que ingresado
      validatePassword(newUser);

      // Creamos el objeto que nos envian y encriptamos el password, definimos un rol por defecto que es CUSTOMER
      User user = new User();
      user.setName(newUser.getName());
      user.setUsername(newUser.getUsername());
      user.setPassword(passwordEncoder.encode(newUser.getPassword()));
      user.setRole(Role.ROLE_CUSTOMER);

      userRepository.save(user);

      return null;
   }

   // Metodo para validar el password
   private void validatePassword(SaveUser newUser) {
      // Verificamos que los dos campo tengan contenido
      if(!StringUtils.hasText(newUser.getPassword()) || !StringUtils.hasText(newUser.getRepeatedPassword())){
         throw new InvalidPasswordException("Password don't match");
      }

      // Validamos que los campos coincidan que tengan lo mismo
      if(!newUser.getPassword().equals(newUser.getRepeatedPassword())){
         throw new InvalidPasswordException("Password don't match");
      }
   }
}
