
package com.example.authjwt.service;

import org.springframework.security.core.Authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.authjwt.repository.UserRepository;
import com.example.authjwt.security.JwtUtil;
import com.example.authjwt.entity.User;
import com.example.authjwt.dto.*;
import org.springframework.security.authentication.AuthenticationManager;


@Service
public class AuthService {

 @Autowired
 AuthenticationManager authManager;


 @Autowired UserRepository repo;
 @Autowired PasswordEncoder encoder;
 @Autowired JwtUtil jwt;

 public User signup(SignupDto dto){
  User u=new User();
  u.setUsername(dto.getUsername());
  u.setEmail(dto.getEmail());
  u.setPassword(encoder.encode(dto.getPassword()));
  u.setRole(dto.getRole());
  return repo.save(u);
 }

 public String login(LoginDto dto) {

  Authentication auth = authManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  dto.getUsername(),
                  dto.getPassword()));

  UserDetails user = (UserDetails) auth.getPrincipal();

  return jwt.generateToken(user);
 }

}
