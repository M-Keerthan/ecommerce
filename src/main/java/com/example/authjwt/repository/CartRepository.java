package com.example.authjwt.repository;

import com.example.authjwt.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.authjwt.entity.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

 Optional<Cart> findByUsername(String username);
}
