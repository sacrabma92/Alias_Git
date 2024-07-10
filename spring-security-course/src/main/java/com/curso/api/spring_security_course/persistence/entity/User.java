package com.curso.api.spring_security_course.persistence.entity;

import com.curso.api.spring_security_course.persistence.repository.util.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "\"user\"")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) return null;

        if (role.getPermissions() == null) return null;

        // Obtenemos los permisos que hemos creado en una lista
        return role.getPermissions().stream()
                .map(each -> each.name())
                .map(each -> new SimpleGrantedAuthority(each))
                .collect(Collectors.toList());
    }


    // Obtener el password
    @Override
    public String getPassword() {
        return password;
    }

    // Saber el nombre de usuario
    @Override
    public String getUsername() {
        return username;
    }

    // Saber si esta cuenta NO ha expirado
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Saber si esta cuenta NO esta bloqueada
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Saber si las credenciales NO han expirado
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Saber si esta habilitada
    @Override
    public boolean isEnabled() {
        return true;
    }

    // ###### Getter y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
