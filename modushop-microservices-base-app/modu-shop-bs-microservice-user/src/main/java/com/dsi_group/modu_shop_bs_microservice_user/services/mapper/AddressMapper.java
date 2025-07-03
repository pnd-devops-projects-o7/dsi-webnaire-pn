package com.dsi_group.modu_shop_bs_microservice_user.services.mapper;

import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.request.AddressRequest;
import com.dsi_group.modu_shop_bs_microservice_user.domain.dtos.response.AddressResponse;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressEntity map(AddressRequest addressRequest);
    AddressResponse map(AddressEntity addressEntity);
}
