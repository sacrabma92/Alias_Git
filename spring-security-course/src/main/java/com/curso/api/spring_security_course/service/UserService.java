package com.curso.api.spring_security_course.service;

import com.curso.api.spring_security_course.dto.SaveUser;
import com.curso.api.spring_security_course.persistence.entity.User;

public interface UserService {

   User registerOneCustomer(SaveUser newUser);

}
