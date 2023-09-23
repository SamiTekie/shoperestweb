package com.example.shoperestweb.controller;

import com.example.shoperestweb.dto.RoleDTO;
import com.example.shoperestweb.mapper.RoleMapper;
import com.example.shoperestweb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return roles;
    }

    @GetMapping("/{id}")
    public RoleDTO getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public void createRole(@RequestBody RoleDTO roleDTO) {
        roleService.createRole(roleDTO);
    }

    @PutMapping("/{id}")
    public RoleDTO updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        return roleService.updateRole(id, roleDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
