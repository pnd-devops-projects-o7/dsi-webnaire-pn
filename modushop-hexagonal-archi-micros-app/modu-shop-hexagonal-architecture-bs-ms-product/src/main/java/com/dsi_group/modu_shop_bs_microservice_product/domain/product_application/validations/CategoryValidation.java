package com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.validations;

import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectExistsAlreadyException;
import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectFieldEmptyException;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.output.OutputCategoryService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryValidation {
    private final OutputCategoryService outputCategoryService;
    private final Logger log = Logger.getLogger(this.getClass().getName());

    public CategoryValidation(OutputCategoryService outputCategoryService) {
        this.outputCategoryService = outputCategoryService;
    }

    public void validateCategoryRequest(String name) {
        if (name.isBlank()) {
            log.warning("Category name field cannot be empty");
            throw new ObjectFieldEmptyException("Category name field cannot be empty");
        }
        if (Boolean.TRUE.equals(outputCategoryService.categoryExistsByName(name))) {
            log.log(Level.SEVERE,()->"product with name "+name+" exists already");
            throw new ObjectExistsAlreadyException("Category with name "+name+" exists already");
        }
    }
}
