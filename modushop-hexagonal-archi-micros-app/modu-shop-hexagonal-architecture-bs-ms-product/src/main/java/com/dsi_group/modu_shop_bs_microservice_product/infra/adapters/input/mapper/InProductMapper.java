package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.mapper;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessDecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessProduct;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessProductRequest;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request.DecreaseStockRequest;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request.ProductPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request.ProductRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.response.ProductResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InProductMapper {
    BusinessProductRequest toBusinessProductRequest(ProductRequestDto productRequestDto);
    BusinessProductRequest toBusinessProductRequest(ProductPatchRequest patchRequest);
    ProductResponseDto toProductResponseDto(BusinessProduct businessProduct);
    BusinessDecreaseStockRequest toBusinessDecreaseStockRequest(DecreaseStockRequest decreaseStockRequest);
}
