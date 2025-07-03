package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.services;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessRemoteUser;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputRemoteUserService;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.mapper.OutRemoteUserMapper;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.domain.UserResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.services.RemoteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutputRemoteUserSvcImpl implements OutputRemoteUserService {
    private final RemoteUserService remoteUserService;
    private final OutRemoteUserMapper outRemoteUserMapper;

    @Override
    public BusinessRemoteUser getBusinessRemoteUserById(Integer userId) {
        UserResponseDto remoteUserResponseDto = remoteUserService.getRemoteUser(userId)
                .getBody();
        return outRemoteUserMapper.toBusinessUser(remoteUserResponseDto);
    }
}
