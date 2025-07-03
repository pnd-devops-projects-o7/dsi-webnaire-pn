package com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.web;

import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategory;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessCategoryRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.BusinessSuccessResponse;
import com.dsi_group.modu_shop_bs_microservice_product.domain.ports.input.InputCategoryService;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.request.CategoryRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.response.CategoryResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.dtos.response.SuccessResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.mapper.InCategoryMapper;
import com.dsi_group.modu_shop_bs_microservice_product.infra.adapters.input.mapper.InSuccessRespMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/products")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    //call input ports
    private final InputCategoryService inputCategoryService;
    private final InCategoryMapper inCategoryMapper;
    private final InSuccessRespMapper inSuccessRespMapper;


    @GetMapping(value = "/categories")
    @PreAuthorize("hasAnyRole('modushop_admin_role','modushop_user_role')")
    public ResponseEntity<Collection<CategoryResponseDto>> findAllCategories() {
        Collection<CategoryResponseDto> businessCategories = inputCategoryService.useCaseGetAllCategories().stream()
                .map(inCategoryMapper::toResponseDto)
                .toList();
        return ResponseEntity.ok(businessCategories);
    }

    @GetMapping(value = "/categories/{id}")
    @PreAuthorize("hasAnyRole('modushop_admin_role','modushop_user_role')")
    public ResponseEntity<CategoryResponseDto> findCategoryById(@PathVariable(value = "id") Integer id) {
        CategoryResponseDto categoryResponseDto = inCategoryMapper
                .toResponseDto(inputCategoryService.useCaseGetCategoryById(id));
        return ResponseEntity.ok(categoryResponseDto);
    }

    @PostMapping(value = "/categories")
    @PreAuthorize("hasRole('modushop_admin_role')")
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        BusinessCategoryRequest businessCategoryRequest = inCategoryMapper.toBusinessCategoryRequest(categoryRequestDto);
        BusinessCategory businessCategory = inputCategoryService.useCaseCreateCategory(businessCategoryRequest);
        return ResponseEntity.ok(inCategoryMapper.toResponseDto(businessCategory));
    }

    @PatchMapping(value = "/categories/{categoryId}")
    @PreAuthorize("hasRole('modushop_admin_role')")
    public ResponseEntity<CategoryResponseDto> updateCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto,
                                                              @PathVariable(value = "categoryId") Integer categoryId) {
        BusinessCategoryRequest businessCategoryRequest = inCategoryMapper.toBusinessCategoryRequest(categoryRequestDto);
        BusinessCategory businessCategory = inputCategoryService.useCaseUpdateCategory(categoryId, businessCategoryRequest);
        return ResponseEntity.ok(inCategoryMapper.toResponseDto(businessCategory));
    }

    @DeleteMapping(value = "/categories/{categoryId}")
    @PreAuthorize("hasRole('modushop_admin_role')")
    public ResponseEntity<SuccessResponseDto> deleteCategory(@PathVariable(value = "categoryId") Integer categoryId) {
        BusinessSuccessResponse businessSuccessResponse = inputCategoryService.useCaseDeleteCategory(categoryId);
        return ResponseEntity.ok(inSuccessRespMapper.intoSuccessResponse(businessSuccessResponse));
    }
}
