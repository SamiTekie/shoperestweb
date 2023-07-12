package com.example.shoperestweb.dto;

import com.example.shoperestweb.model.Product;
import com.example.shoperestweb.model.ProductCategory;

import java.util.Optional;

public class DTOConverter {
    public static ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(Optional.ofNullable(product.getProductId()).map(Long::intValue).orElse(null));

//        if (product.getProductId() != null) {
//            dto.setProductId(product.getProductId().intValue());
//        } else {
//            dto.setProductId(null);
//        }

        dto.setProductName(product.getProductName());
        dto.setProductPrice(product.getProductPrice());
        dto.setProductDescription(product.getProductDescription());
        dto.setProductCategory(convertToDTO(product.getProductCategory()));
        return dto;
    }

    public static ProductCategoryDTO convertToDTO(ProductCategory productCategory) {
        ProductCategoryDTO dto = new ProductCategoryDTO();
        dto.setProductCategoryId(productCategory.getProductCategoryId());
        dto.setProductCategoryName(productCategory.getProductCategoryName());
        return dto;
    }

    public static Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        //   This line sets the productId property of the product object.
//    It retrieves the productId from the ProductDTO object using productDTO.getProductId().
//    The Optional.ofNullable method wraps the productId in an Optional object
//    to handle possible null values. The map method applies a mapping function
//    to convert the Long value to a Long using Long::valueOf.
//    If the productId is null, the orElse method sets the value to null in the product object.
        product.setProductId(Optional.ofNullable(productDTO.getProductId()).map(Long::valueOf).orElse(null));
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductDescription(productDTO.getProductDescription());

        if (productDTO.getProductCategory() != null) {
            ProductCategoryDTO productCategoryDTO = productDTO.getProductCategory();
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryDTO.getProductCategoryId());
            productCategory.setProductCategoryName(productCategoryDTO.getProductCategoryName());
            product.setProductCategory(productCategory);
        }

        return product;
    }


    public static ProductCategory convertToEntity(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(productCategoryDTO.getProductCategoryId());
        productCategory.setProductCategoryName(productCategoryDTO.getProductCategoryName());
        return productCategory;
    }
}
