package com.example.authjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.authjwt.entity.CartItem;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartUsername(String username);
}
