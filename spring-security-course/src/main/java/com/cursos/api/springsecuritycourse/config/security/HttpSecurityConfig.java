package com.cursos.api.springsecuritycourse.config.security;

import com.cursos.api.springsecuritycourse.config.security.filter.JwtAuthenticationFilter;
import com.cursos.api.springsecuritycourse.persistence.util.Role;
import com.cursos.api.springsecuritycourse.persistence.util.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

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
                buildRequestMatchers(authReqConfig);
            })
                .exceptionHandling(exceptionConfig -> {
                    exceptionConfig.authenticationEntryPoint(authenticationEntryPoint);
                    exceptionConfig.accessDeniedHandler(accessDeniedHandler);
                })
                .build();
        return filterChain;
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {

    // Autorización de enpoinds de Prodcuts

        authReqConfig.requestMatchers(HttpMethod.GET, "/products")
                .hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.GET, "/products/{productId}")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.POST, "/products")
                .hasRole(Role.ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}")
                .hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}/disable")
                .hasRole(Role.ADMINISTRATOR.name());


                // Autorización de enpoinds de Prodcuts

        authReqConfig.requestMatchers(HttpMethod.GET, "/category")
                .hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.GET, "/category/{categoryId}")
                .hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.POST, "/category")
                .hasRole(Role.ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/category/{categoryId}")
                .hasAnyAuthority(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/category/{categoryd}/disable")
                .hasRole(Role.ADMINISTRATOR.name());

        authReqConfig.requestMatchers(HttpMethod.GET, "/profile")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name(),
                Role.CUSTOMER.name());

        //AUtorización de Enpoind Publicos

        // Unicas rutas que son publicas y cualquiera pueden acceder
        authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();

        // Todas las demas rutas estaran bloqueadas, necesitaran autenticación
        authReqConfig.anyRequest().authenticated();
    }

    private static void buildRequestMatchersv2(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {

//        /* AUtorización de Enpoind Publicos */
//
//        // Unicas rutas que son publicas y cualquiera pueden acceder
//        authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
//        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
//        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();
//
//        // Todas las demas rutas estaran bloqueadas, necesitaran autenticación
//        authReqConfig.anyRequest().authenticated();
    }
}
