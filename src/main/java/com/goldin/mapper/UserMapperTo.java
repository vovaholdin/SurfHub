package com.goldin.mapper;

import com.goldin.entity.User;
import com.goldin.mapper.dto.UserTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperTo {
    UserTo toDto(User user);
    User toEntity(UserTo user);
}
