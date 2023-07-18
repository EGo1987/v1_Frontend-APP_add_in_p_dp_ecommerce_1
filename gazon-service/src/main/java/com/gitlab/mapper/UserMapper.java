package com.gitlab.mapper;

import com.gitlab.dto.UserDto;
import com.gitlab.model.*;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

}

