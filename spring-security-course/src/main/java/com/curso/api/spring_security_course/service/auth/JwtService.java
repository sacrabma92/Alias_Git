package com.curso.api.spring_security_course.service.auth;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
public class JwtService {

   @Value("${security.jwt.expiration-in-minutes}")
   private Long EXPIRATION_IN_MINUTES;

   @Value("${security.jwt.secret_key}")
   private String SECRET_KEY;

   public String generateToken(UserDetails user, Map<String, Objects> extraClaims) {

      Date issuedAt = new Date(System.currentTimeMillis());
      Date expiration = new Date( (EXPIRATION_IN_MINUTES * 60 * 1000) + issuedAt.getTime() );
      String jwt = Jwts.builder()
              .setClaims(extraClaims)
              .subject(user.getUsername())
              .setIssuedAt(issuedAt)
              .setExpiration(expiration)
              .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
              .signWith(generateKey(), SignatureAlgorithm.HS256)
              .compact();

      return jwt;
   }

   private Key generateKey() {
      byte[] key = SECRET_KEY.getBytes();
      return Keys.hmacShaKeyFor(key);
   }

}
