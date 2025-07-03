package com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.services;

import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectExistsAlreadyException;
import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectFieldEmptyException;
import com.dsi_group.modu_shop_bs_microservice_product.domain.exceptions.ObjectNotFoundException;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategory;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategoryRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessSuccessResponse;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.input.InputCategoryService;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.output.OutputCategoryService;
import com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.mappers.BusinessCategoryMapper;
import com.dsi_group.modu_shop_bs_microservice_product.domain.product_application.validations.CategoryValidation;

import java.time.Instant;
import java.util.Collection;
import java.util.logging.Logger;

public class CategoryUseCasesImpl implements InputCategoryService {

    private final OutputCategoryService outputCategoryService;
    private final CategoryValidation categoryValidation;
    private final Logger log = Logger.getLogger(this.getClass().getName());

    public CategoryUseCasesImpl(OutputCategoryService outputCategoryService, CategoryValidation categoryValidation) {
        this.outputCategoryService = outputCategoryService;
        this.categoryValidation = categoryValidation;
    }

    @Override
    public Collection<BusinessCategory> useCaseGetAllCategories() {
        return outputCategoryService.getAllCategories();
    }

    @Override
    public BusinessCategory useCaseGetCategoryById(Integer categoryId) {
        return outputCategoryService.getCategoryById(categoryId)
                .orElseThrow(() -> {
                    log.warning("Category id not found: " + categoryId);
                    return new ObjectNotFoundException(String.format("Category id not found: %s", categoryId));
                });
    }

    @Override
    public BusinessCategory useCaseCreateCategory(BusinessCategoryRequest categoryRequest) {
        categoryValidation.validateCategoryRequest(categoryRequest.name());
        BusinessCategory businessCategory = BusinessCategoryMapper.toBusinessCategory(categoryRequest);
        // set create date
        AuditableDateG.updateDate(businessCategory);
        // call output port to send for registration in db
      return outputCategoryService.createCategory(businessCategory);
    }

    @Override
    public BusinessCategory useCaseUpdateCategory(Integer categoryId, BusinessCategoryRequest categoryRequest) {
        BusinessCategory businessCategory = useCaseGetCategoryById(categoryId);
        if (categoryRequest.name() != null) {
            log.info(String.format("Updating category name %s", categoryRequest.name()));
            if (outputCategoryService.categoryExistsByName(categoryRequest.name())) {
                log.warning(String.format("Category name already exists %s", categoryRequest.name()));
                throw new ObjectExistsAlreadyException(String.format("Category with this name %s already exists", categoryRequest.name()));
            }
            businessCategory.setName(categoryRequest.name());
        }
        if (categoryRequest.description() != null) {
            log.info(String.format("Updating category description %s", categoryRequest.description()));
            businessCategory.setDescription(categoryRequest.description());
        }
        //set update date
        AuditableDateG.updateDate(businessCategory);
        // call output port
        return outputCategoryService.updateCategory(businessCategory);
    }

    @Override
    public BusinessSuccessResponse useCaseDeleteCategory(Integer categoryId) {
        useCaseGetCategoryById(categoryId);// if not exists raise business exception
        outputCategoryService.deleteCategory(categoryId);
        return BusinessSuccessResponse.builder()
                .code(200)
                .status("SUCCESS")
                .message("Category successfully deleted")
                .instant(Instant.now())
                .build();
    }
}
