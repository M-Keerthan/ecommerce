
package com.example.authjwt.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.authjwt.repository.UserRepository;
import com.example.authjwt.security.JwtUtil;
import com.example.authjwt.entity.User;
import com.example.authjwt.dto.*;

@Service
public class AuthService {

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

 public String login(LoginDto dto){
  User u=repo.findByUsername(dto.getUsername()).orElseThrow();
  if(!encoder.matches(dto.getPassword(),u.getPassword()))
   throw new RuntimeException("Invalid password");
  return jwt.generateToken(u.getUsername(),u.getRole().name());
 }
}
