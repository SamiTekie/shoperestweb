package com.example.shoperestweb.controller;

import com.example.shoperestweb.dto.RoleDTO;
import com.example.shoperestweb.mapper.RoleMapper;
import com.example.shoperestweb.model.Role;
import com.example.shoperestweb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void createRole(RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO); // Convert RoleDTO to Role
        roleRepository.save(role);
    }

    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO); // Convert RoleDTO to Role
        role.setRoleId(id);
        Role updatedRole = roleRepository.save(role);
        if (updatedRole != null) {
            return roleMapper.toDTO(updatedRole);
        }
        return null;
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
