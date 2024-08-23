package com.udemy.elearning.controllers;

import com.udemy.elearning.dto.CategoryRequest;
import com.udemy.elearning.mapper.CategoryResponse;
import com.udemy.elearning.models.Category;
import com.udemy.elearning.repository.CategoryRepository;
import com.udemy.elearning.services.CategoryService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }



    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest categoryRequest) throws BadRequestException {
        Category categoryCreate = categoryService.create(categoryRequest);
        CategoryResponse categoryResponse = new CategoryResponse(categoryCreate);
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping()
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryId,
                                                   @Valid @RequestBody Category categoryDetails) throws BadRequestException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BadRequestException("Category not found for this id :: " + categoryId));

        category.setName(categoryDetails.getName());
        final Category updatedCategory = categoryRepository.save(category);
        return ResponseEntity.ok(updatedCategory);
    }


}
