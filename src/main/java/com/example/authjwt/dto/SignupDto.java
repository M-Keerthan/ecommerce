
package com.example.authjwt.dto;
import lombok.Data;
import com.example.authjwt.entity.Role;

@Data
public class SignupDto {
 private String username;
 private String email;
 private String password;
 private Role role;
}
