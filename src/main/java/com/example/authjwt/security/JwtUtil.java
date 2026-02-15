package com.example.authjwt.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;


import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {




 private final String SECRET =
         "mysecretkeymysecretkeymysecretkey123456";

 private final Key key =
         Keys.hmacShaKeyFor(SECRET.getBytes());

 // Generate JWT with roles
 public String generateToken(UserDetails user) {

  List<String> roles = user.getAuthorities()
          .stream()
          .map(GrantedAuthority::getAuthority)
          .toList();

  return Jwts.builder()
          .setSubject(user.getUsername())
          .claim("roles", roles)
          .setIssuedAt(new Date())
          .setExpiration(
                  new Date(System.currentTimeMillis()+86400000))
          .signWith(key)
          .compact();
 }

 public String extractUsername(String token) {
  return getClaims(token).getSubject();
 }

 public List<String> extractRoles(String token) {
  return getClaims(token).get("roles", List.class);
 }

 public boolean validateToken(String token, String username) {
  return extractUsername(token).equals(username)
          && !isExpired(token);
 }

 private boolean isExpired(String token) {
  return getClaims(token)
          .getExpiration()
          .before(new Date());
 }

 private Claims getClaims(String token) {
  return Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token)
          .getBody();
 }
}
