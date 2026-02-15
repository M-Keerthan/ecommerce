
package com.example.authjwt.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

 @Autowired
 private JwtUtil jwtUtil;

 @Override
 protected void doFilterInternal(HttpServletRequest req,
                                 HttpServletResponse res,
                                 FilterChain chain)
         throws ServletException, IOException {

  // ⭐ SKIP AUTH ENDPOINTS
  if (req.getServletPath().startsWith("/auth")) {
   chain.doFilter(req, res);
   return;
  }

  String header = req.getHeader("Authorization");

  if (header != null && header.startsWith("Bearer ")) {

   String token = header.substring(7);

   try {
    String username = jwtUtil.extractUsername(token);

    if (username != null &&
            SecurityContextHolder.getContext()
                    .getAuthentication() == null) {

     var roles = jwtUtil.extractRoles(token);

     var authorities = roles.stream()
             .map(SimpleGrantedAuthority::new)
             .toList();

     UsernamePasswordAuthenticationToken auth =
             new UsernamePasswordAuthenticationToken(
                     username, null, authorities);

     SecurityContextHolder.getContext()
             .setAuthentication(auth);
    }

   } catch (Exception e) {
    System.out.println("JWT error: " + e.getMessage());
   }
  }

  chain.doFilter(req, res);
 }

}
