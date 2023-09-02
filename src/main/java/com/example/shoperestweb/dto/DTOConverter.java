package com.example.shoperestweb.dto;

import com.example.shoperestweb.model.Product;
import com.example.shoperestweb.model.ProductCategory;
import com.example.shoperestweb.model.Role;
import com.example.shoperestweb.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            ProductCategoryDTO productCategoryDTO = productDTO.getProductCategory();
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId((long) productCategoryDTO.getProductCategoryId());
            productCategory.setProductCategoryName(productCategoryDTO.getProductCategoryName());
            product.setProductCategory(productCategory);
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

        dto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));

        return dto;
    }

    public static User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); // Assuming password is already hashed
        // Convert role names to roles
        List<Role> roles = userDTO.getRoles().stream().map(roleName -> {
            Role role = new Role();
            role.setName(roleName); // Use setName instead of setRoleName
            return role;
        }).collect(Collectors.toList());
        user.setRoles(new HashSet<>(roles)); // Make sure to create a HashSet for roles
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
