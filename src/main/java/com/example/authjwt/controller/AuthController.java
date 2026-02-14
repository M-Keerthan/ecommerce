
package com.example.authjwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.authjwt.service.AuthService;
import com.example.authjwt.dto.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

 @Autowired
 private AuthService service;

 @PostMapping("/signup")
 public ResponseEntity<String> signup(@RequestBody SignupDto dto) {
  service.signup(dto);
  return ResponseEntity
          .status(HttpStatus.CREATED)
          .body("User registered successfully");
 }

 @PostMapping("/login")
 public ResponseEntity<String> login(@RequestBody LoginDto dto) {
  String token = service.login(dto);
  return ResponseEntity.ok(token);
 }
}
