package com.example.authjwt.service;

import com.example.authjwt.entity.Cart;
import com.example.authjwt.entity.CartItem;
import com.example.authjwt.entity.Product;
import com.example.authjwt.repository.CartItemRepository;
import com.example.authjwt.repository.CartRepository;
import com.example.authjwt.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

 @Autowired
 private CartRepository cartRepo;

 @Autowired
 private CartItemRepository cartItemRepo;

 @Autowired
 private ProductRepository productRepo;

 public CartItem add(String username, Long productId, int qty) {

  Product product = productRepo.findById(productId)
          .orElseThrow(() -> new RuntimeException("Product not found"));

  Cart cart = cartRepo.findByUsername(username)
          .orElseGet(() -> {
           Cart c = new Cart();
           c.setUsername(username);
           return cartRepo.save(c);
          });

  CartItem item = new CartItem();
  item.setCart(cart);
  item.setProduct(product);
  item.setQuantity(qty);

  return cartItemRepo.save(item);
 }

 public List<CartItem> viewCart(String username) {
  return cartItemRepo.findByCartUsername(username);
 }

 public void deleteItem(Long id, String username) {

  CartItem item = cartItemRepo.findById(id)
          .orElseThrow(() -> new RuntimeException("Item not found"));

  if (!item.getCart().getUsername().equals(username)) {
   throw new RuntimeException("Unauthorized delete");
  }

  cartItemRepo.delete(item);
 }
}