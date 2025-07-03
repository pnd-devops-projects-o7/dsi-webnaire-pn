package com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.local.mapper;

import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessDecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_order.domain.models.remote.BusinessRemoteProduct;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.domain.DecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_order.infra.adapters.output.remote.domain.ProductResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutRemoteProductMapper {
    BusinessRemoteProduct toBusinessRemoteProduct(ProductResponseDto productResponseDto);
    DecreaseStockRequest toBusinessDecreaseStockRequest(BusinessDecreaseStockRequest decreaseStockRequest);
}
