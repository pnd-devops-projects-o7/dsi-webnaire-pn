package com.dsi_group.modu_shop_bs_microservice_order.domain.ports.output;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessDecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessRemoteProduct;

public interface OutputRemoteProductService {
    BusinessRemoteProduct getBusinessRemoteProductById(Integer productId);
    void decreaseProductStock(Integer productId, BusinessDecreaseStockRequest businessDecreaseStockRequest);
}
