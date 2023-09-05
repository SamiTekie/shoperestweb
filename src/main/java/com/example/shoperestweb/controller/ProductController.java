package com.example.shoperestweb.controller;

import com.example.shoperestweb.dto.DTOConverter;
import com.example.shoperestweb.dto.ProductDTO;
import com.example.shoperestweb.model.Product;
import com.example.shoperestweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products.stream()
                .map(DTOConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return DTOConverter.convertToDTO(product);
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        Product product = DTOConverter.convertToEntity(productDTO);
        Product createdProduct = productService.createProduct(product);
        return DTOConverter.convertToDTO(createdProduct);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product product = DTOConverter.convertToEntity(productDTO);
        Product updatedProduct = productService.updateProduct(id, product);
        return DTOConverter.convertToDTO(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
