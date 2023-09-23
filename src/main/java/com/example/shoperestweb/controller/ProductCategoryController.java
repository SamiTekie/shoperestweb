package com.example.shoperestweb.controller;

import com.example.shoperestweb.dto.ProductCategoryDTO;
import com.example.shoperestweb.mapper.ProductCategoryMapper;
import com.example.shoperestweb.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-categories")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;
    private final ProductCategoryMapper productCategoryMapper;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryService = productCategoryService;
        this.productCategoryMapper = productCategoryMapper;
    }

    @GetMapping
    public List<ProductCategoryDTO> getAllProductCategories() {
        List<ProductCategoryDTO> productCategories = productCategoryService.getAllProductCategories();
        return productCategories;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> getProductCategoryById(@PathVariable Long id) {
        ProductCategoryDTO productCategory = productCategoryService.getProductCategoryById(id);
        if (productCategory != null) {
            return ResponseEntity.ok(productCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ProductCategoryDTO createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategoryDTO createdProductCategory = productCategoryService.createProductCategory(productCategoryDTO);
        return createdProductCategory;
    }

    @PutMapping("/{id}")
    public ProductCategoryDTO updateProductCategory(@PathVariable Long id, @RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategoryDTO updatedProductCategory = productCategoryService.updateProductCategory(id, productCategoryDTO);
        return updatedProductCategory;
    }

    @DeleteMapping("/{id}")
    public void deleteProductCategory(@PathVariable Long id) {
        productCategoryService.deleteProductCategory(id);
    }
}
