package com.example.shoperestweb.controller;

import com.example.shoperestweb.dto.DTOConverter;
import com.example.shoperestweb.dto.ProductCategoryDTO;
import com.example.shoperestweb.model.ProductCategory;
import com.example.shoperestweb.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product-categories")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public List<ProductCategoryDTO> getAllProductCategories() {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        return productCategories.stream()
                .map(DTOConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> getProductCategoryById(@PathVariable Long id) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        if (productCategory != null) {
            ProductCategoryDTO dto = DTOConverter.convertToDTO(productCategory);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ProductCategoryDTO createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = DTOConverter.convertToEntity(productCategoryDTO);

        // Set the default status to true when creating a category
        productCategory.setActive(true);

        ProductCategory createdProductCategory = productCategoryService.createProductCategory(productCategory);
        return DTOConverter.convertToDTO(createdProductCategory);
    }


    @PutMapping("/{id}")
    public ProductCategoryDTO updateProductCategory(@PathVariable Long id, @RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = DTOConverter.convertToEntity(productCategoryDTO);

        // Ensure the status is preserved when updating a category
        ProductCategory existingProductCategory = productCategoryService.getProductCategoryById(id);
        if (existingProductCategory != null) {
            productCategory.setActive(existingProductCategory.getActive()); // Use getActive() here
        }

        ProductCategory updatedProductCategory = productCategoryService.updateProductCategory(id, productCategory);
        return DTOConverter.convertToDTO(updatedProductCategory);
    }


    @DeleteMapping("/{id}")
    public void deleteProductCategory(@PathVariable Long id) {
        productCategoryService.deleteProductCategory(id);
    }
}
