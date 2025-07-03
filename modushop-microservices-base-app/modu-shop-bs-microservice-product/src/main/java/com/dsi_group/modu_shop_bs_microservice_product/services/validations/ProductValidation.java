package com.dsi_group.modu_shop_bs_microservice_product.services.validations;

import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.ProductRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.Category;
import com.dsi_group.modu_shop_bs_microservice_product.exceptions.ObjectExistsAlreadyException;
import com.dsi_group.modu_shop_bs_microservice_product.exceptions.ObjectFieldEmptyException;
import com.dsi_group.modu_shop_bs_microservice_product.exceptions.ObjectNotFoundException;
import com.dsi_group.modu_shop_bs_microservice_product.repositories.CategoryRepository;
import com.dsi_group.modu_shop_bs_microservice_product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductValidation {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public Category validateProductRequest(ProductRequestDto productRequestDto) {
        if (productRequestDto.name().isEmpty()) {
            log.error("Product name is empty");
            throw new ObjectFieldEmptyException("Product request name is empty");
        }

        if (productRequestDto.description().isEmpty()) {
            log.error("Product description is empty");
            throw new ObjectFieldEmptyException("Product description is empty");
        }

        if (productRequestDto.price() == null || productRequestDto.price().doubleValue() <1) {
            log.error("Product price is empty or less than 1");
            throw new ObjectFieldEmptyException("Product price is empty or less than 1");
        }
        if (productRequestDto.stock() == null || productRequestDto.stock() < 2) {
            log.error("Product stock is empty or less than 2");
            throw new ObjectFieldEmptyException("Product stock is empty or less than 2");
        }
        if(productRequestDto.categoryId() == null) {
            log.error("Product category is missing");
            throw new ObjectFieldEmptyException("Product categoryId is missing");
        }

        if(productRepository.existsByName(productRequestDto.name())) {
            log.error("Product with name {} already exists", productRequestDto.name());
            throw new ObjectExistsAlreadyException(String.format("Product with name %s already exists",
                    productRequestDto.name()));
        }

        return categoryRepository.findById(productRequestDto.categoryId())
                .orElseThrow(() -> {
                    log.error("Category with id {} not found", productRequestDto.categoryId());
                    return new ObjectNotFoundException(String.format("Category with id %s not found",
                            productRequestDto.categoryId()));
                });
    }
}
