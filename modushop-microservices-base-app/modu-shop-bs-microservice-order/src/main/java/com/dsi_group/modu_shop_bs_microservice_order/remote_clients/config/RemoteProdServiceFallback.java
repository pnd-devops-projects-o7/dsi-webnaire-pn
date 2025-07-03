package com.dsi_group.modu_shop_bs_microservice_order.remote_clients.config;

import com.dsi_group.modu_shop_bs_microservice_order.exceptions.RemoteServiceUnreachableException;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.domain.DecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.domain.ProductResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.remote_clients.services.RemoteProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RemoteProdServiceFallback implements RemoteProductService {
    @Override
    public ResponseEntity<ProductResponseDto> getRemoteProductById(Integer productId) {
        log.error(getExceptionMessage());
       throw new RemoteServiceUnreachableException(getExceptionMessage());
    }

    @Override
    public void decreaseProductStock(Integer productId, DecreaseStockRequest decreaseStockRequest) {
        log.error(getExceptionMessage());
    }

    private String getExceptionMessage() {
        return "Remote product service unreachable or remote product object not found";
    }
}
