package com.dsi_group.modu_shop_bs_microservice_user.services;

import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.request.UserPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.request.UserRequestDto;
import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.response.UserResponseDto;

import java.util.Collection;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(UserPatchRequest userPatchRequest, Integer userId);

    UserResponseDto getUser(Integer userId);

    Collection<UserResponseDto> getAllUsers();
}
