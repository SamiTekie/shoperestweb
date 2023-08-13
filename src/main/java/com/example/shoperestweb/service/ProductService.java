package com.example.shoperestweb.service;

import com.example.shoperestweb.model.Product;
import com.example.shoperestweb.model.ProductCategory;
import com.example.shoperestweb.repository.ProductCategoryRepository;
import com.example.shoperestweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        Optional<Product> productOptional = productRepository.findById((long) id);
        return productOptional.orElse(null);
    }

    public Product createProduct(Product product) {
        if (product.getProductCategory() != null) {
            Integer productCategoryId = Math.toIntExact(product.getProductCategory().getProductCategoryId()); // Use Integer here
            Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(productCategoryId);
            if (productCategoryOptional.isPresent()) {
                ProductCategory productCategory = productCategoryOptional.get();
                product.setProductCategory(productCategory);
            } else {
                throw new IllegalArgumentException("Invalid Product Category");
            }
        }
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product product) {
        Optional<Product> existingProductOptional = productRepository.findById((long) id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            if (product.getProductCategory() != null) {
                Integer productCategoryId = Math.toIntExact(product.getProductCategory().getProductCategoryId()); // Use Integer here
                Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(productCategoryId);
                if (productCategoryOptional.isPresent()) {
                    ProductCategory productCategory = productCategoryOptional.get();
                    existingProduct.setProductName(product.getProductName());
                    existingProduct.setProductPrice(product.getProductPrice());
                    existingProduct.setProductDescription(product.getProductDescription());
                    existingProduct.setProductCategory(productCategory);
                } else {
                    throw new IllegalArgumentException("Invalid Product Category");
                }
            } else {
                existingProduct.setProductName(product.getProductName());
                existingProduct.setProductPrice(product.getProductPrice());
                existingProduct.setProductDescription(product.getProductDescription());
                existingProduct.setProductCategory(null);
            }

            return productRepository.save(existingProduct);
        }
        return null;
    }


    public void deleteProduct(int id) {
        productRepository.deleteById((long) id);
    }
}
