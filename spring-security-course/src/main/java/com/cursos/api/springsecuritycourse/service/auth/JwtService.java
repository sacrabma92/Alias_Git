package com.cursos.api.springsecuritycourse.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    // Metodo para generar el TOKEN
    public String generateToken(UserDetails user,
                                Map<String, Object> extraClaims){
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date( (EXPIRATION_IN_MINUTES * 60 * 1000) + issuedAt.getTime() );

        // COnstruimos el JWT cada una de sus parte
        String jwt = Jwts.builder()
                // Header del JWT
                .header()
                .type("JWT")
                .and()

                // Payload del JWT
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(extraClaims)

                // Firma del JWT
                .signWith(generateKey(), Jwts.SIG.HS256)
                .compact();
        return jwt;
    }

    // se encarga de generar una clave criptográfica que se utiliza en operaciones
    // HMAC (Código de Autenticación de Mensajes con Hash), particularmente para el
    // manejo de JWT (JSON Web Token)
    private SecretKey generateKey(){
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    // Metodo que va a extraer el username del Payload
    public String extractUsername(String jwt) {
        // Extraemos todos los datos del claim y va a tomar el propietario del claim
        return extractAllClaims(jwt).getSubject();
    }

    // Metodos que van a validar el Payload del JWT
    private Claims extractAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith( generateKey() )
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
