package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.services;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessDecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessRemoteProduct;
import com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output.OutputRemoteProductService;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.mapper.OutRemoteProductMapper;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.domain.DecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.domain.ProductResponseDto;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.services.RemoteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutputRemoteProductSvcImpl implements OutputRemoteProductService {
    private final RemoteProductService remoteProductService;
    private final OutRemoteProductMapper outRemoteProductMapper;

    @Override
    public BusinessRemoteProduct getBusinessRemoteProductById(Integer productId) {
        ProductResponseDto remoteProductResponseDto= remoteProductService.getRemoteProductById(productId)
                .getBody();
        return outRemoteProductMapper.toBusinessRemoteProduct(remoteProductResponseDto);
    }

    @Override
    public void decreaseProductStock(Integer productId, BusinessDecreaseStockRequest businessDecreaseStockRequest) {
        DecreaseStockRequest decreaseStockRequest =outRemoteProductMapper
                .toBusinessDecreaseStockRequest(businessDecreaseStockRequest);
        remoteProductService.decreaseProductStock(productId,decreaseStockRequest);
    }
}
