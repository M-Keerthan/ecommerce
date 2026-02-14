
package com.example.authjwt.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
 private final String SECRET="mysecretkeymysecretkeymysecretkey123456";
 private final Key key=Keys.hmacShaKeyFor(SECRET.getBytes());

 public String generateToken(String username,String role){
  return Jwts.builder()
   .setSubject(username)
   .claim("role",role)
   .setIssuedAt(new Date())
   .setExpiration(new Date(System.currentTimeMillis()+86400000))
   .signWith(key).compact();
 }

 public String extractUsername(String token){
  return Jwts.parserBuilder()
   .setSigningKey(key).build()
   .parseClaimsJws(token)
   .getBody().getSubject();
 }
}
