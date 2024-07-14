package com.cursos.api.springsecuritycourse.service.impl;

import com.cursos.api.springsecuritycourse.dto.SaveUser;
import com.cursos.api.springsecuritycourse.exception.InvalidPasswordException;
import com.cursos.api.springsecuritycourse.persistence.entity.User;
import com.cursos.api.springsecuritycourse.persistence.repository.UserRepository;
import com.cursos.api.springsecuritycourse.persistence.util.Role;
import com.cursos.api.springsecuritycourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public User registerOneCustomer(SaveUser newUser) {
        // Validamos la contraseña que ingreso
        validatePassword(newUser);

        // Creamos el objeto que nos envia y encriptamos el password,
        // definimos un rol que por defecto que es CUSTOMER
        User user = new User();
        user.setName(newUser.getName());
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);

        return userRepository.save(user);
    }

    // Metodo para validar el password
    private void validatePassword(SaveUser dto){
        // Verificamos que los dos campos tengan contenido
        if(!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Password don't match");
        }

        // Validamos que los campos coincidan que tengan el mismo contenido
        if(!dto.getPassword().equals(dto.getRepeatedPassword())){
            throw new InvalidPasswordException("Password don't match");
        }
    }
}
