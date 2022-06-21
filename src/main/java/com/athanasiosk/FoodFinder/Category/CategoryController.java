package com.athanasiosk.FoodFinder.Category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryRepo categoryRepo;

    CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    // GET all categories
    @GetMapping("/api/v1/categories")
    List<Category> all() {
        return categoryRepo.findAll();
    }

    // POST Add a new Category
    @PostMapping("/api/v1/categories")
//    @ResponseStatus(code = HttpStatus.CREATED, reason = "CREATED")
    Category newCategory(@RequestBody Category newCategory) {
        return categoryRepo.save(newCategory);
    }

    // GET Find a single Category by the Id
    @GetMapping("/api/v1/categories/{id}")
    Category oneCategory(@PathVariable Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    /// PUT Replace a Category by the Id
    @PutMapping("/api/v1/categories/{id}")
    Category replaceCategory(@RequestBody Category newCategory, @PathVariable Long id) {
        return categoryRepo.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    return categoryRepo.save(category);
                })
                .orElseGet(() -> {
                    newCategory.setId(id);
                    return categoryRepo.save(newCategory);
                });
    }

    // DELETE Delete a Category by Id
    @DeleteMapping("/api/v1/categories/{id}")
    void deleteCategory(@PathVariable Long id) {
        categoryRepo.deleteById(id);
    }
}