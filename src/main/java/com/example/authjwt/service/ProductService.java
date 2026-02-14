
package com.example.authjwt.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.authjwt.repository.ProductRepository;
import com.example.authjwt.mapper.ProductMapper;
import com.example.authjwt.dto.ProductDto;
import java.util.*;

@Service
public class ProductService {
 @Autowired ProductRepository repo;
 @Autowired ProductMapper mapper;

 public ProductDto save(ProductDto dto){
  return mapper.toDto(repo.save(mapper.toEntity(dto)));
 }

 public List<ProductDto> all(){
  return repo.findAll().stream().map(mapper::toDto).toList();
 }

 public void delete(Long id){ repo.deleteById(id); }
}
