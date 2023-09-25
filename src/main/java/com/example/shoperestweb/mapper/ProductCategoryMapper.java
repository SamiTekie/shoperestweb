package com.example.shoperestweb.mapper;

import com.example.shoperestweb.dto.ProductCategoryDTO;
import com.example.shoperestweb.model.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ProductCategoryMapper {
    ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

    @Mapping(source = "productCategoryId", target = "id")
    ProductCategoryDTO toDTO(ProductCategory productCategory);

    @Mapping(source = "id", target = "productCategoryId")
    ProductCategory toEntity(ProductCategoryDTO productCategoryDTO);
}
