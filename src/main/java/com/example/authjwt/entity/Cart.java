
package com.example.authjwt.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.*;

@Entity @Data
public class Cart {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;

 @OneToOne
 private User user;

 private String username;

 @OneToMany(mappedBy="cart",cascade=CascadeType.ALL)
 private List<CartItem> items=new ArrayList<>();
}
