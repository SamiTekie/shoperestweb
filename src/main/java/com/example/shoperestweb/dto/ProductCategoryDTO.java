package com.example.shoperestweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDTO {
    private Long productCategoryId;
    private String productCategoryName;
    private Boolean active;

}
