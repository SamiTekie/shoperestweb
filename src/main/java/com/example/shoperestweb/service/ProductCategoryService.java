package com.example.shoperestweb.service;
import com.example.shoperestweb.model.ProductCategory;
import com.example.shoperestweb.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory getProductCategoryById(int id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public ProductCategory updateProductCategory(int id, ProductCategory productCategory) {
        ProductCategory existingProductCategory = productCategoryRepository.findById(id).orElse(null);
        if (existingProductCategory != null) {
            existingProductCategory.setProductCategoryName(productCategory.getProductCategoryName());
            return productCategoryRepository.save(existingProductCategory);
        }
        return null;
    }

    public void deleteProductCategory(int id) {
        productCategoryRepository.deleteById(id);
    }
}
