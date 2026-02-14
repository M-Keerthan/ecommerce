
package com.example.authjwt.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter{

 @Autowired JwtUtil jwtUtil;
 @Autowired UserDetailsService uds;

 protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)
 throws ServletException,IOException{

  String header=req.getHeader("Authorization");
  if(header!=null && header.startsWith("Bearer ")){
   String token=header.substring(7);
   String username=jwtUtil.extractUsername(token);
   var user=uds.loadUserByUsername(username);
   var auth=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
   SecurityContextHolder.getContext().setAuthentication(auth);
  }
  chain.doFilter(req,res);
 }
}
