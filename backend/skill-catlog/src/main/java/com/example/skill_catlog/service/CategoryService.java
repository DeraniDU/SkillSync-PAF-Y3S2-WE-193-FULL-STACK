package com.example.skill_catlog.service;

import com.example.skill_catlog.dto.CategoryRequest;
import com.example.skill_catlog.dto.CategoryResponse;
import com.example.skill_catlog.exception.ResourceNotFoundException;
import com.example.skill_catlog.model.SkillCategory;
import com.example.skill_catlog.repository.SkillCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final SkillCategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest request) {
        SkillCategory category = new SkillCategory();
        category.setName(request.getName());

        SkillCategory savedCategory = categoryRepository.save(category);
        return mapToCategoryResponse(savedCategory);
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToCategoryResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(String id) {
        SkillCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return mapToCategoryResponse(category);
    }

    public CategoryResponse updateCategory(String id, CategoryRequest request) {
        SkillCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        category.setName(request.getName());
        SkillCategory updatedCategory = categoryRepository.save(category);
        return mapToCategoryResponse(updatedCategory);
    }

    public void deleteCategory(String id) {
        SkillCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        // Check if category has associated skills
        if (category.getSkills() != null && !category.getSkills().isEmpty()) {
            throw new IllegalStateException("Cannot delete category with associated skills");
        }

        categoryRepository.delete(category);
    }

    private CategoryResponse mapToCategoryResponse(SkillCategory category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}