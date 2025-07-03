package com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessRemoteUser;

public interface OutputRemoteUserService {
    BusinessRemoteUser getBusinessRemoteUserById(Integer userId);
}
