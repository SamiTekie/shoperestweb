package com.example.shoperestweb.service;

import com.example.shoperestweb.model.ProductCategory;
import com.example.shoperestweb.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory getProductCategoryById(int id) {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(id);
        return productCategoryOptional.orElse(null);
    }

    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public ProductCategory updateProductCategory(int id, ProductCategory productCategory) {
        Optional<ProductCategory> existingProductCategoryOptional = productCategoryRepository.findById(id);
        if (existingProductCategoryOptional.isPresent()) {
            ProductCategory existingProductCategory = existingProductCategoryOptional.get();
            existingProductCategory.setProductCategoryName(productCategory.getProductCategoryName());
            return productCategoryRepository.save(existingProductCategory);
        }
        return null;
    }

    public void deleteProductCategory(int id) {
        productCategoryRepository.deleteById(id);
    }
}
