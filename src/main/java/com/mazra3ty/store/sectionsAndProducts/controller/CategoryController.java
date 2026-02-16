package com.mazra3ty.store.sectionsAndProducts.controller;


import com.mazra3ty.store.sectionsAndProducts.DTO.MainCategory.CategoryRequest;
import com.mazra3ty.store.sectionsAndProducts.DTO.MainCategory.CategoryResponse;
import com.mazra3ty.store.sectionsAndProducts.DTO.MainCategory.CategorySimpleResponse;
import com.mazra3ty.store.sectionsAndProducts.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Tag(name = "Main Categories", description = "CRUD REST APIs to CREATE, UPDATE, FETCH, AND DELETE Main Categories details")
@RestController
@AllArgsConstructor
@RequestMapping("/main-category")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(description = "api To Create A New Main Category")
    @PostMapping("/create")
    public ResponseEntity<CategorySimpleResponse> createCategory(@Valid @RequestBody CategoryRequest request) {

        return new ResponseEntity<>(categoryService.createCategory(request), HttpStatus.CREATED);
    }

    @Operation(description = "api to Get One Category By Id")
    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }


    @Operation(description = "api to Get All Category")
    @GetMapping("/list")
    public ResponseEntity<List<CategorySimpleResponse>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }


    @Operation(description = "api To Make Update Categories Details By Id")
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> updateCategoryById(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return new ResponseEntity<>(categoryService.updateCategoryById(id, request), HttpStatus.OK);
    }


    @Operation(description = "api To Make Soft Delete for Categories By Id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        categoryService.softDeleteById(id);
        return ResponseEntity.ok(Map.of(
                "Status", 200,
                "Success", true,
                "MessageAR", "تم حذف القسم رقم " + id + " بنجاح",
                "MessageEN", "This Category " + id + " Has Been Deleted",
                "Id Deleted", id,
                "Timestamp", LocalDateTime.now().toString()
        ));
    }


    @Operation(description = "api to Get All Deleted Categories")
    @GetMapping("/deleted-list")
    public ResponseEntity<List<CategoryResponse>> getAllDeletedCategories() {
        return new ResponseEntity<>(categoryService.getAllDeletedCategories(), HttpStatus.OK);
    }
}
