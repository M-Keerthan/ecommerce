
package com.example.authjwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.authjwt.service.CartService;
import java.security.Principal;

@RestController
@RequestMapping("/user/cart")
public class CartController {

 @Autowired
 private CartService service;

 // ADD TO CART
 @PostMapping("/add")
 public ResponseEntity<?> add(
         @RequestParam Long productId,
         @RequestParam int qty,
         Principal p) {

  Object cartItem = service.add(p.getName(), productId, qty);

  return ResponseEntity
          .status(HttpStatus.CREATED)
          .body(cartItem);
 }

 // VIEW CART ITEMS
 @GetMapping
 public ResponseEntity<?> viewCart(Principal p) {
  return ResponseEntity.ok(service.viewCart(p.getName()));
 }

 // DELETE CART ITEM
 @DeleteMapping("/{id}")
 public ResponseEntity<String> delete(
         @PathVariable Long id,
         Principal p) {

  service.deleteItem(id, p.getName());

  return ResponseEntity.ok("Cart item removed successfully");
 }
}

