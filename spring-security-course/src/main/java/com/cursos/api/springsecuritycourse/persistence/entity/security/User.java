package com.cursos.api.springsecuritycourse.persistence.entity.security;

import com.cursos.api.springsecuritycourse.persistence.util.RoleEnum;
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
    private String name;
    @Column(unique = true)
    private String username;
    private String password;

    private Role role;

    // El propósito del método es devolver una colección de permisos
    // (autorizaciones) que tiene el usuario, en forma de objetos GrantedAuthority.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Si el rol que recibimo es null salimos
        if(role == null) return null;
        // Si en permisos NO llega nada salimos.
        if(role.getPermissions() == null) return null;

        List<SimpleGrantedAuthority> authorities = role.getPermissions().stream()
                .map(each -> each.getOperation().getName())
                .map(each -> new SimpleGrantedAuthority(each))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.getName()));
        return authorities;
    }

    // Obtenemos el password
    @Override
    public String getPassword() {
        return password;
    }
    // Para obtener el nombre de usuario
    @Override
    public String getUsername() {
        return username;
    }
    // Para saber si esta cuenta NO ha expirado
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // Para saber si las credenciales NO estan bloqueadas
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // Para saber si las credenciales NO han expirado
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // Saber si esta habilitado
    @Override
    public boolean isEnabled() {
        return true;
    }

    // GETTERS Y SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
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
