package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.mapper;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategory;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategoryRequest;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request.CategoryRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.response.CategoryResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.output.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InCategoryMapper {
    CategoryResponseDto toResponseDto(BusinessCategory businessCategory);
    BusinessCategoryRequest toBusinessCategoryRequest(CategoryRequestDto categoryRequestDto);
}
