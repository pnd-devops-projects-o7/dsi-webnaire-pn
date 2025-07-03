package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.mapper;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessRemoteUser;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.domain.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutRemoteUserMapper {
    BusinessRemoteUser toBusinessUser(UserResponseDto userResponseDto);
}
