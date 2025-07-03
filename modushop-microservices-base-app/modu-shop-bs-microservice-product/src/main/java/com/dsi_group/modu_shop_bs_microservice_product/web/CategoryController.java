package com.dsi_group.modu_shop_bs_microservice_product.web;

import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.CategoryPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.CategoryRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.CategoryResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.SuccessResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/products")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(value = "/categories")
    public ResponseEntity<Collection<CategoryResponseDto>> findAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<CategoryResponseDto> findCategoryById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping(value = "/categories")
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(categoryService.createCategory(categoryRequestDto));
    }

    @PatchMapping(value = "/categories/{categoryId}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@Valid @RequestBody CategoryPatchRequest patchRequest,
                                                              @PathVariable(value = "categoryId") Integer categoryId) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, patchRequest));
    }

    @DeleteMapping(value = "/categories/{categoryId}")
    public ResponseEntity<SuccessResponseDto> deleteCategory(@PathVariable(value = "categoryId") Integer categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}
