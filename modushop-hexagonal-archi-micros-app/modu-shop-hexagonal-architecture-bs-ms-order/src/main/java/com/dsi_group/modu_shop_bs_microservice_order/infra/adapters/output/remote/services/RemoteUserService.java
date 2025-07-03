package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.services;

import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.config.JwtInterceptorPropagator;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.domain.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${remote.user-service.name}",
        url = "${remote.user-service.url}",
        path = "${remote.user-service.context-path}",
        configuration = JwtInterceptorPropagator.class)
public interface RemoteUserService {

    @GetMapping(value = "/{clientId}")
    ResponseEntity<UserResponseDto> getRemoteUser(@PathVariable(value = "clientId") Integer clientId);
}
