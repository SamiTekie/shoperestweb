package com.example.shoperestweb.service;

import com.example.shoperestweb.dto.RoleDTO;
import com.example.shoperestweb.mapper.RoleMapper;
import com.example.shoperestweb.model.Role;
import com.example.shoperestweb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RoleDTO getRoleById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.map(roleMapper::toDTO).orElse(null);
    }

    public void createRole(RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        roleRepository.save(role);
    }

    public RoleDTO updateRole(Long id, RoleDTO roleDTO) {
        Optional<Role> existingRoleOptional = roleRepository.findById(id);
        if (existingRoleOptional.isPresent()) {
            Role existingRole = existingRoleOptional.get();
            existingRole.setRoleName(roleDTO.getRoleName());
            Role updatedRole = roleRepository.save(existingRole);
            return roleMapper.toDTO(updatedRole);
        }
        return null;
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
