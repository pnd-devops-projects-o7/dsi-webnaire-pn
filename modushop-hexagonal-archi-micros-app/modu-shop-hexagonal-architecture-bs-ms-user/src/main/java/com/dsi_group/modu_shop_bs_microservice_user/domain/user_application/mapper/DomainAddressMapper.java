package com.dsi_group.modu_shop_bs_microservice_user.domain.user_application.mapper;

import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessAddress;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.DomainAddressRequest;

public class DomainAddressMapper {
    private DomainAddressMapper() {}

    public static BusinessAddress toAddress(DomainAddressRequest domainAddressRequest){
        return BusinessAddress.builder()
                .num(domainAddressRequest.num())
                .street(domainAddressRequest.street())
                .zipCode(domainAddressRequest.zipCode())
                .city(domainAddressRequest.city())
                .country(domainAddressRequest.country())
                .build();
    }
}
