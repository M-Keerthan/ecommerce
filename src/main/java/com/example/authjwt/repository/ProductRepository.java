
package com.example.authjwt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.authjwt.entity.Product;
public interface ProductRepository extends JpaRepository<Product,Long>{

}
