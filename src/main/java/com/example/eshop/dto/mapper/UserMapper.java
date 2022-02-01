package com.example.eshop.dto.mapper;

import com.example.eshop.dto.UserCreatorDto;
import com.example.eshop.dto.UserDto;
import com.example.eshop.dto.UserVerificationDto;
import com.example.eshop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

     UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

     UserDto toDto(User user);

     User toUser(UserDto dto);
     User toUser(UserCreatorDto dto);
     User toUser(UserVerificationDto dto);

     List<UserDto> toDtos(List<User> usesrs);
}
