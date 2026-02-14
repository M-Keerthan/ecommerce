
package com.example.authjwt.mapper;
import org.mapstruct.Mapper;
import com.example.authjwt.entity.Product;
import com.example.authjwt.dto.ProductDto;

@Mapper(componentModel="spring")
public interface ProductMapper {
 Product toEntity(ProductDto dto);
 ProductDto toDto(Product entity);
}
