
package com.example.authjwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.authjwt.service.ProductService;
import com.example.authjwt.dto.ProductDto;
import java.util.*;

@RestController
@RequestMapping("/admin/products")
public class ProductController {

 @Autowired
 private ProductService service;


 @PostMapping
 public ResponseEntity<ProductDto> add(@RequestBody ProductDto dto) {
  ProductDto saved = service.save(dto);
  return ResponseEntity.status(HttpStatus.CREATED).body(saved);
 }

 // GET ALL PRODUCTS
 @GetMapping
 public ResponseEntity<List<ProductDto>> all() {
  return ResponseEntity.ok(service.all());
 }

 // DELETE PRODUCT
 @DeleteMapping("/{id}")
 public ResponseEntity<String> delete(@PathVariable Long id) {
  service.delete(id);
  return ResponseEntity.ok("Product deleted successfully");
 }
}
