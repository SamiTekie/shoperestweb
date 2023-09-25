package com.example.shoperestweb.mapper;

import com.example.shoperestweb.dto.UserDTO;
import com.example.shoperestweb.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userId", target = "id")
    UserDTO toDTO(User user);

    @Mapping(source = "id", target = "userId")
    User toEntity(UserDTO userDTO);
}
