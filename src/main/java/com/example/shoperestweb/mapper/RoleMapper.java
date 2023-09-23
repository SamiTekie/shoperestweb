package com.example.shoperestweb.mapper;

import com.example.shoperestweb.dto.RoleDTO;
import com.example.shoperestweb.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(source = "roleId", target = "id")
    RoleDTO toDTO(Role role);

    @Mapping(source = "id", target = "roleId")
    Role toEntity(RoleDTO roleDTO);
}
