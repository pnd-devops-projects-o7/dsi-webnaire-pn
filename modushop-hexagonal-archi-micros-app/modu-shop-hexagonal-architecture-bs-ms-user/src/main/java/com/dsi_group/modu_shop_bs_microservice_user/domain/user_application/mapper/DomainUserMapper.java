package com.dsi_group.modu_shop_bs_microservice_user.domain.user_application.mapper;

import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessAddress;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.DomainUserRequest;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessUser;

public class DomainUserMapper {
    private DomainUserMapper() {
    }

    public static BusinessUser toUser(DomainUserRequest domainUserRequest) {

        BusinessAddress businessAddress = DomainAddressMapper.toAddress(domainUserRequest.addressRequest());

        return BusinessUser.builder()
                .firstname(domainUserRequest.firstname())
                .lastname(domainUserRequest.lastname())
                .email(domainUserRequest.email())
                .phoneNumber(domainUserRequest.phoneNumber())
                .address(businessAddress)
                .build();
    }
}
