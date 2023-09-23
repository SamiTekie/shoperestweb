package com.example.shoperestweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int productId;
    private String productName;
    private double productPrice;
    private String productDescription;
    private ProductCategoryDTO productCategory; // Use ProductCategoryDTO here

}
