package com.example.shoperestweb.service;

import com.example.shoperestweb.dto.RoleDTO;
import com.example.shoperestweb.model.Role;
import com.example.shoperestweb.repository.RoleRepository;
import com.example.shoperestweb.mapper.RoleMapper; // Import the RoleMapper

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper; // Inject the RoleMapper

    @Autowired
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper; // Initialize the RoleMapper
    }

    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RoleDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            return roleMapper.toDTO(role);
        }
        return null;
    }

    public void createRole(Role role) {
        roleRepository.save(role);
    }

    public RoleDTO updateRole(Long id, Role role) {
        Optional<Role> existingRoleOptional = roleRepository.findById(id);
        if (existingRoleOptional.isPresent()) {
            Role existingRole = existingRoleOptional.get();
            existingRole.setName(role.getName());
            Role updatedRole = roleRepository.save(existingRole);
            return roleMapper.toDTO(updatedRole);
        }
        return null;
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
