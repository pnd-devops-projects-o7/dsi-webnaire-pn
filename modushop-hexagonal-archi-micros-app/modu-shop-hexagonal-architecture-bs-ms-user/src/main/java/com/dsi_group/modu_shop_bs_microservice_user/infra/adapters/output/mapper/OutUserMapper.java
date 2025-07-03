package com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.mapper;

import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessUser;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutUserMapper {
    UserEntity toUserEntity(BusinessUser businessUser);
    BusinessUser toUser(UserEntity userEntity);
}
