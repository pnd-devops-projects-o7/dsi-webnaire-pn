package com.dsi_group.modu_shop_bs_microservice_user.services.mapper;

import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.request.UserRequestDto;
import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.response.UserResponseDto;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntity(UserRequestDto userRequestDto);
    @Mapping(source = "addressEntity",target = "addressResponse")
    UserResponseDto toUserResponseDto(UserEntity userEntity);
}
