package com.curso.api.spring_security_course.controller;

import com.curso.api.spring_security_course.dto.RegisterUser;
import com.curso.api.spring_security_course.dto.SaveUser;
import com.curso.api.spring_security_course.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<RegisterUser> registerOne(@RequestBody @Valid SaveUser newUser){
        RegisterUser registerUser = authenticationService.registerOneCustomer(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUser);
    }
}
