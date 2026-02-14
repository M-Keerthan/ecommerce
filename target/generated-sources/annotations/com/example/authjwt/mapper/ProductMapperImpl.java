package com.example.authjwt.mapper;

import com.example.authjwt.dto.ProductDto;
import com.example.authjwt.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-14T13:18:24+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        return product;
    }

    @Override
    public ProductDto toDto(Product entity) {
        if ( entity == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        return productDto;
    }
}
