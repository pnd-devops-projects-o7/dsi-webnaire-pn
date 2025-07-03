package com.dsi_group.modu_shop_bs_microservice_product.domain.ports.output;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategory;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategoryRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessProduct;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.response.SuccessResponseDto;

import java.util.Collection;
import java.util.Optional;

public interface OutputCategoryService {
    Collection<BusinessCategory> getAllCategories();
    Optional<BusinessCategory> getCategoryById(Integer categoryId);
    BusinessCategory createCategory(BusinessCategory businessCategory);
    BusinessCategory updateCategory(BusinessCategory businessCategory);
    void deleteCategory(Integer categoryId);
    Boolean categoryExistsByName(String name);
}
