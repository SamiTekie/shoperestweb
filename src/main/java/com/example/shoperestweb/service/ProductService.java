package com.example.shoperestweb.service;

import com.example.shoperestweb.model.Product;
import com.example.shoperestweb.model.ProductCategory;
import com.example.shoperestweb.repository.ProductCategoryRepository;
import com.example.shoperestweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    public Product createProduct(Product product) {
        // Fetch the associated ProductCategory object if it is not null
        if (product.getProductCategory() != null) {
            ProductCategory productCategory = productCategoryRepository
                    .findById(product.getProductCategory().getProductCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Product Category"));

            // Set the ProductCategory object in the Product entity
            product.setProductCategory(productCategory);
        }

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            // Fetch the associated ProductCategory object if it is not null
            if (product.getProductCategory() != null) {
                ProductCategory productCategory = productCategoryRepository
                        .findById(product.getProductCategory().getProductCategoryId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid Product Category"));

                // Update the fields in the existing Product entity
                existingProduct.setProductName(product.getProductName());
                existingProduct.setProductPrice(product.getProductPrice());
                existingProduct.setProductDescription(product.getProductDescription());
                existingProduct.setProductCategory(productCategory);
            } else {
                // Handle the case where the new product does not have a ProductCategory
                existingProduct.setProductName(product.getProductName());
                existingProduct.setProductPrice(product.getProductPrice());
                existingProduct.setProductDescription(product.getProductDescription());
                existingProduct.setProductCategory(null); // Set the ProductCategory to null
            }

            return productRepository.save(existingProduct);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
