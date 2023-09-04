package com.example.shoperestweb.dto;

import com.example.shoperestweb.model.Product;
import com.example.shoperestweb.model.ProductCategory;
import com.example.shoperestweb.model.Role;
import com.example.shoperestweb.model.User;

import java.util.Optional;

public class DTOConverter {
    public static ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(Optional.ofNullable(product.getProductId()).map(Long::intValue).orElse(null));
        dto.setProductName(product.getProductName());
        dto.setProductPrice(product.getProductPrice());
        dto.setProductDescription(product.getProductDescription());
        dto.setProductCategory(convertToDTO(product.getProductCategory()));
        return dto;
    }

    public static ProductCategoryDTO convertToDTO(ProductCategory productCategory) {
        ProductCategoryDTO dto = new ProductCategoryDTO();
        dto.setProductCategoryId(Math.toIntExact(productCategory.getProductCategoryId()));
        dto.setProductCategoryName(productCategory.getProductCategoryName());
        return dto;
    }

    public static Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(Optional.ofNullable(productDTO.getProductId()).map(Long::valueOf).orElse(null));
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductDescription(productDTO.getProductDescription());

        if (productDTO.getProductCategory() != null) {
            product.setProductCategory(convertToEntity(productDTO.getProductCategory()));
        }

        return product;
    }

    public static ProductCategory convertToEntity(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId((long) productCategoryDTO.getProductCategoryId());
        productCategory.setProductCategoryName(productCategoryDTO.getProductCategoryName());
        return productCategory;
    }

    public static UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword()); // Assuming password is already hashed in the User object

        if (user.getRole() != null) {
            dto.setRole(user.getRole().getName()); // Set the role name
        }

        return dto;
    }


    public static User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); // Assuming password is already hashed

        // Create a role from the role name
        Role role = new Role();
        if (userDTO.getRole() != null) {
            role.setName(userDTO.getRole()); // Set the role name
        }

        user.setRole(role);

        return user;
    }




    public static RoleDTO convertToDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(role.getRoleId());
        dto.setRoleName(role.getName()); // Use getName instead of getRoleName
        return dto;
    }

    public static Role convertToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setName(roleDTO.getRoleName()); // Use setName instead of setRoleName
        return role;
    }
}
