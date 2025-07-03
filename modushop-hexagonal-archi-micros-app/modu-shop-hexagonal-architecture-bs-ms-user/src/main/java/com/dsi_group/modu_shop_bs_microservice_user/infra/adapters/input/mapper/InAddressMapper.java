package com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.mapper;

import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessAddress;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.DomainAddressRequest;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.dtos.request.AddressRequestDto;
import com.dsi_group.modu_shop_bs_microservice_user.infra.adapters.input.dtos.response.AddressResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InAddressMapper {
    DomainAddressRequest toAddressRequest(AddressRequestDto addressRequestDto);
    AddressResponse toAddressResponse(BusinessAddress businessAddress);
}
