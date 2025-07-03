package com.dsi_group.modu_shop_bs_microservice_product.services;

import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.CategoryPatchRequest;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.request.CategoryRequestDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.CategoryResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.dtos.response.SuccessResponseDto;
import com.dsi_group.modu_shop_bs_microservice_product.domain.models.Category;
import com.dsi_group.modu_shop_bs_microservice_product.exceptions.ObjectExistsAlreadyException;
import com.dsi_group.modu_shop_bs_microservice_product.exceptions.ObjectNotFoundException;
import com.dsi_group.modu_shop_bs_microservice_product.repositories.CategoryRepository;
import com.dsi_group.modu_shop_bs_microservice_product.services.mapper.CategoryMapper;
import com.dsi_group.modu_shop_bs_microservice_product.services.validations.CategoryValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryValidation categoryValidation;

    @Override
    public Collection<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toResponseDto).toList();
    }

    @Override
    public CategoryResponseDto getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            log.error("Category with id {} not found", categoryId);
            return new ObjectNotFoundException(String.format("Category with id %s not found", categoryId));
        });

        return categoryMapper.toResponseDto(category);
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        categoryValidation.validateCategoryRequest(categoryRequestDto.name());
        Category mappedCategory = categoryMapper.toModel(categoryRequestDto);
        AuditableDateG.updateDate(mappedCategory);
        return categoryMapper.toResponseDto(categoryRepository.save(mappedCategory));
    }

    @Override
    public CategoryResponseDto updateCategory(Integer categoryId, CategoryPatchRequest patchRequest) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            log.error("Category with this id {} not found", categoryId);
            return new ObjectNotFoundException(String.format("Category with this id %s not found", categoryId));
        });
        if (patchRequest.name() != null) {
            log.info("Updating category with name {}", patchRequest.name());
            if (categoryByNameExists(patchRequest.name())) {
                log.error("Category with this name {} already exists", patchRequest.name());
                throw new ObjectExistsAlreadyException(String.format("Category with this name %s already exists", patchRequest.name()));
            }
            category.setName(patchRequest.name());
        }
        if (patchRequest.description() != null) {
            log.info("Updating category with description {}", patchRequest.description());
            category.setDescription(patchRequest.description());
        }
        return categoryMapper.toResponseDto(categoryRepository.save(category));
    }

    @Override
    public SuccessResponseDto deleteCategory(Integer categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            log.info("category with id {} not found", categoryId);
            throw new ObjectNotFoundException(String.format("category with id %s not found", categoryId));
        }
        categoryRepository.deleteById(categoryId);
        return SuccessResponseDto.builder()
                .code(200)
                .status("Success")
                .message(String.format("Category with id %s deleted successfully", categoryId))
                .timestamp(Timestamp.from(Instant.now()))
                .build();
    }

    private boolean categoryByNameExists(String name) {
        return categoryRepository.existsByName(name);
    }
}
