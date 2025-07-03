package com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.validations;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategory;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessProductRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.output.OutputCategoryService;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.output.OutputProductService;
import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectExistsAlreadyException;
import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectFieldEmptyException;
import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectNotFoundException;

import java.util.logging.Logger;

public class ProductValidation {
    private final OutputCategoryService outputCategoryService;
    private final OutputProductService outputProductService;
    private final Logger log = Logger.getLogger(this.getClass().getName());

    public ProductValidation(OutputCategoryService outputCategoryService, OutputProductService outputProductService) {
        this.outputCategoryService = outputCategoryService;
        this.outputProductService = outputProductService;
    }


    public BusinessCategory validateProductRequest(BusinessProductRequest businessProductRequest) {
        if (businessProductRequest.name().isEmpty()) {
            log.warning("Product name is empty");
            throw new ObjectFieldEmptyException("Product request name is empty");
        }

        if (businessProductRequest.description().isEmpty()) {
            log.warning("Product description is empty");
            throw new ObjectFieldEmptyException("Product description is empty");
        }

        if (businessProductRequest.price() == null || businessProductRequest.price().doubleValue() <1) {
            log.warning("Product price is empty or less than 1");
            throw new ObjectFieldEmptyException("Product price is empty or less than 1");
        }
        if (businessProductRequest.stock() == null || businessProductRequest.stock() < 2) {
            log.warning("Product stock is empty or less than 2");
            throw new ObjectFieldEmptyException("Product stock is empty or less than 2");
        }
        if(businessProductRequest.categoryId() == null) {
            log.warning("Product category is missing");
            throw new ObjectFieldEmptyException("Product categoryId is missing");
        }

        if(outputProductService.productExistsByName(businessProductRequest.name())) {
            log.warning("Product with the same name already exists");
            throw new ObjectExistsAlreadyException(String.format("Product with name %s already exists",
                    businessProductRequest.name()));
        }

        return outputCategoryService.getCategoryById(businessProductRequest.categoryId())
                .orElseThrow(() -> {
                    log.warning("Category with id {} not found");
                    return new ObjectNotFoundException(String.format("Category with id %s not found",
                            businessProductRequest.categoryId()));
                });
    }
}
