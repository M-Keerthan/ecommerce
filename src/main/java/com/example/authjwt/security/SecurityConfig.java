
package com.example.authjwt.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

 @Bean PasswordEncoder encoder(){
  return new BCryptPasswordEncoder();
 }

 @Bean SecurityFilterChain filter(HttpSecurity http,JwtFilter jwt) throws Exception{
  http.csrf(cs->cs.disable())
   .authorizeHttpRequests(auth->auth
    .requestMatchers("/auth/**").permitAll()
    .requestMatchers("/admin/**").hasRole("ADMIN")
    .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
    .anyRequest().authenticated())
   .addFilterBefore(jwt,UsernamePasswordAuthenticationFilter.class);
  return http.build();
 }

 @Bean JwtFilter jwtFilter(){ return new JwtFilter(); }
}
