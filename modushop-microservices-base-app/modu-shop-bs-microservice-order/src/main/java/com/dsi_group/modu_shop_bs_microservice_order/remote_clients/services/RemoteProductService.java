package com.dsi_group.modu_shop_bs_microservice_order.remote_clients.services;

import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.config.RemoteProdServiceFallback;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.domain.DecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.domain.ProductResponseDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${remote.product-service.name}",
        url = "${remote.product-service.url}",
        path = "${remote.product-service.context-path}",
        fallback = RemoteProdServiceFallback.class)
public interface RemoteProductService {

    @GetMapping(value = "/{productId}")
    ResponseEntity<ProductResponseDto> getRemoteProductById(@PathVariable(value = "productId") Integer productId);

    @PostMapping(value = "/{productId}")
    void decreaseProductStock(@PathVariable(value = "productId") Integer productId,
                              @RequestBody @Valid DecreaseStockRequest decreaseStockRequest);
}
