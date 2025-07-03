package com.dsi_group.modu_shop_bs_microservice_product.domain.ports.input;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategory;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategoryRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessSuccessResponse;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.response.SuccessResponseDto;

import java.util.Collection;

public interface InputCategoryService {
    Collection<BusinessCategory> useCaseGetAllCategories();
    BusinessCategory useCaseGetCategoryById(Integer categoryId);
    BusinessCategory useCaseCreateCategory(BusinessCategoryRequest categoryRequest);
    BusinessCategory useCaseUpdateCategory(Integer categoryId, BusinessCategoryRequest categoryRequest);
    BusinessSuccessResponse useCaseDeleteCategory(Integer categoryId);

}
