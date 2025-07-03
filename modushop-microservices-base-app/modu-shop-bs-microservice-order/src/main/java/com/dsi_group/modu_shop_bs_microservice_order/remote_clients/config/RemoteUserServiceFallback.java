package com.dsi_group.modu_shop_bs_microservice_order.remote_clients.config;

import com.dsi_group.modu_shop_bs_microservice_order.exceptions.RemoteServiceUnreachableException;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.domain.UserResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.services.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RemoteUserServiceFallback implements RemoteUserService {
    private static final String exceptionMessage = "remote user service api unreachable or remote object not found";
    @Override
    public ResponseEntity<UserResponseDto> getRemoteUser(Integer clientId) {
        log.error(exceptionMessage);
       throw new RemoteServiceUnreachableException(exceptionMessage);
    }
}
