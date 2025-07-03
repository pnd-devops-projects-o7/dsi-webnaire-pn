package com.dsi_group.modu_shop_bs_microservice_user.domain.ports.output;

import com.dsi_group.modu_shop_bs_microservice_user.domain.models.BusinessUser;

import java.util.Collection;
import java.util.Optional;

public interface OutputUserService {
    BusinessUser createUser(BusinessUser businessUser);

    BusinessUser updateUser(BusinessUser businessUser);

    Optional<BusinessUser> getUser(Integer userId);

    Collection<BusinessUser> getAllUsers();

    Collection<BusinessUser> getByEmail(String email);

    Optional<BusinessUser> getUserByPhoneNumber(String phoneNumber);
}
