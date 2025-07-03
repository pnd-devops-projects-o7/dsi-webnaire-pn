package com.dsi_group.modu_shop_bs_microservice_user.domain.ports.input;


import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessUser;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.DomainUserPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_user.domain.models.DomainUserRequest;

import java.util.Collection;

public interface InputUserService {
    BusinessUser useCaseCreateUser(DomainUserRequest domainUserRequest);

    BusinessUser useCaseUpdateUser(DomainUserPatchRequest domainUserPatchRequest, Integer userId);

    BusinessUser useCaseGetUser(Integer userId);

    Collection<BusinessUser> useCaseGetAllUsers();
}
