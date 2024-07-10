package com.ms_security.controllers;

import com.ms_security.entities.Role;
import com.ms_security.entities.User;
import com.ms_security.repository.RoleRepository;
import com.ms_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User store(@RequestBody User user){
     return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User show(@PathVariable String id){
        User theUser = userRepository.findById(id).orElseThrow(null);
        return theUser;
    }

    @PutMapping("/{id}")
    public User update(@PathVariable String id, @RequestBody User user){
        User usuarioActual = userRepository.findById(id).orElseThrow(null);
        if(usuarioActual != null){
            usuarioActual.setName(user.getName());
            usuarioActual.setEmail(user.getEmail());
            usuarioActual.setPassword(user.getPassword());
            return userRepository.save(usuarioActual);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void destroy(@PathVariable String id){
        User user = userRepository.findById(id).orElseThrow(null);
        if(user != null){
            userRepository.delete(user);
        }
    }

    @PutMapping("/{user_id}/role/{role_id}")
    public User matchUserRole(@PathVariable String user_id,
                              @PathVariable String role_id){
        User usuarioActual = userRepository.findById(user_id).orElseThrow(null);
        Role roleActual = roleRepository.findById(role_id).orElseThrow(null);

        if(usuarioActual != null && roleActual != null){
            usuarioActual.setRole(roleActual);

            return userRepository.save(usuarioActual);
        }else{
            return null;
        }
    }
}
