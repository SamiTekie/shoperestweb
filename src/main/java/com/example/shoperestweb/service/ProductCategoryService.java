package com.example.shoperestweb.service;

import com.example.shoperestweb.dto.ProductCategoryDTO;
import com.example.shoperestweb.mapper.ProductCategoryMapper;
import com.example.shoperestweb.model.ProductCategory;
import com.example.shoperestweb.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper; // Initialize the mapper
    }

    public List<ProductCategoryDTO> getAllProductCategories() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        return productCategories
                .stream()
                .map(productCategoryMapper::toDTO)
                .collect(Collectors.toList()); // Use the mapper to convert to DTOs
    }

    public ProductCategoryDTO getProductCategoryById(Long id) {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(id);
        return productCategoryOptional.map(productCategoryMapper::toDTO).orElse(null); // Use the mapper to convert to DTO
    }

    public ProductCategoryDTO createProductCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryMapper.toEntity(productCategoryDTO); // Use the mapper to convert to an entity
        // Ensure the status is set to true when creating a category
        productCategory.setActive(true);
        ProductCategory createdProductCategory = productCategoryRepository.save(productCategory);
        return productCategoryMapper.toDTO(createdProductCategory); // Use the mapper to convert back to DTO
    }

    public ProductCategoryDTO updateProductCategory(Long id, ProductCategoryDTO productCategoryDTO) {
        Optional<ProductCategory> existingProductCategoryOptional = productCategoryRepository.findById(id);
        return existingProductCategoryOptional.map(existingProductCategory -> {
            ProductCategory updatedProductCategory = productCategoryMapper.toEntity(productCategoryDTO); // Use the mapper to convert to an entity
            // Preserve the existing status
            updatedProductCategory.setActive(existingProductCategory.getActive());
            updatedProductCategory.setProductCategoryId(id); // Set the ID to the existing ID
            ProductCategory savedProductCategory = productCategoryRepository.save(updatedProductCategory);
            return productCategoryMapper.toDTO(savedProductCategory); // Use the mapper to convert back to DTO
        }).orElse(null);
    }

    public void deleteProductCategory(Long id) {
        productCategoryRepository.deleteById(id);
    }
}
