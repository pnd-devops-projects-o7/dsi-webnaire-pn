package com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.mapper;

import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessAddress;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.output.entities.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutAddressMapper {
    AddressEntity toAddressEntity(BusinessAddress businessAddress);
    BusinessAddress toAddress(AddressEntity addressEntity);
}
