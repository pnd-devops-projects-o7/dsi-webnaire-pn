package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.mapper;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessSuccessResponse;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.response.SuccessResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InSuccessRespMapper {
    SuccessResponseDto intoSuccessResponse(BusinessSuccessResponse businessSuccessResponse);
}
