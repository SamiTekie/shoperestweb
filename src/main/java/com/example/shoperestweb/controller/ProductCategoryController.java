package com.example.shoperestweb.controller;

import com.example.shoperestweb.dto.DTOConverter;
import com.example.shoperestweb.dto.ProductCategoryDTO;
import com.example.shoperestweb.model.ProductCategory;
import com.example.shoperestweb.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ProductCategoryDTO getProductCategoryById(@PathVariable int id) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        return DTOConverter.convertToDTO(productCategory);
    }

    @PostMapping
    public ProductCategoryDTO createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = DTOConverter.convertToEntity(productCategoryDTO);
        ProductCategory createdProductCategory = productCategoryService.createProductCategory(productCategory);
        return DTOConverter.convertToDTO(createdProductCategory);
    }

    @PutMapping("/{id}")
    public ProductCategoryDTO updateProductCategory(@PathVariable int id, @RequestBody ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = DTOConverter.convertToEntity(productCategoryDTO);
        ProductCategory updatedProductCategory = productCategoryService.updateProductCategory(id, productCategory);
        return DTOConverter.convertToDTO(updatedProductCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteProductCategory(@PathVariable int id) {
        productCategoryService.deleteProductCategory(id);
    }
}
