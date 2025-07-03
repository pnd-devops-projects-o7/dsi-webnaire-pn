package com.dsi_group.modu_shop_bs_microservice_product.services.validations;

import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.CategoryRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.exceptions.ObjectExistsAlreadyException;
import com.dsi_group.modu_shop_bs_microservice_product.exceptions.ObjectFieldEmptyException;
import com.dsi_group.modu_shop_bs_microservice_product.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CategoryValidation {
    private final CategoryRepository categoryRepository;

    public void validateCategoryRequest(String name)  {
        if(name.isBlank()){
            log.error("Category with name {} cannot be empty", name);
            throw new ObjectFieldEmptyException(String.format("Category with name %s cannot be empty", name));
        }
        if(categoryRepository.existsByName(name)) {
            log.error("Category with name {} already exists", name);
            throw new ObjectExistsAlreadyException(String.format("Category with name %s already exists", name));
        }
    }
}
