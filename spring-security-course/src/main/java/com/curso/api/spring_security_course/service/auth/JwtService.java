package com.curso.api.spring_security_course.service.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtService {

   @Value("${security.jwt.expiration-in-minutes}")
   private Long EXPIRATION_IN_MINUTES;

   @Value("${security.jwt.secret_key}")
   private String SECRET_KEY;

   public String generateToken(UserDetails user, Map<String, Object> extraClaims) {

      Date issuedAt = new Date(System.currentTimeMillis());
      Date expiration = new Date( (EXPIRATION_IN_MINUTES * 60 * 1000) + issuedAt.getTime() );
      // Construimos el JWT cada una de sus partes.
      String jwt = Jwts.builder()
        // Cabecera del JWT
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

   private SecretKey generateKey() {
      byte[] key = SECRET_KEY.getBytes();
      return Keys.hmacShaKeyFor(key);
   }

}
