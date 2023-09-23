package com.example.shoperestweb.mapper;

import com.example.shoperestweb.dto.ProductDTO;
import com.example.shoperestweb.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "productId", target = "id")
    ProductDTO toDTO(Product product);

    @Mapping(source = "id", target = "productId")
    Product toEntity(ProductDTO productDTO);
}
