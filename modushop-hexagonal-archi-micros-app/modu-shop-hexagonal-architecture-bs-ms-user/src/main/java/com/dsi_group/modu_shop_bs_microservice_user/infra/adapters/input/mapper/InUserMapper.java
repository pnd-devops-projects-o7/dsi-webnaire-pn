package com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.mapper;

import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessUser;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.DomainUserPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.DomainUserRequest;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.dtos.request.UserPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.dtos.request.UserRequestDto;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.dtos.response.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InUserMapper {
    DomainUserRequest toDomainUserRequest(UserRequestDto userRequestDto);
    DomainUserPatchRequest toDomainUserPatchRequest(UserPatchRequest userPatchRequest);
    UserResponseDto toUserResponseDto(BusinessUser businessUser);
}
