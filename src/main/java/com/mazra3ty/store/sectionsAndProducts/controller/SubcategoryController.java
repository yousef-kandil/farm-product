package com.mazra3ty.store.sectionsAndProducts.controller;

import com.mazra3ty.store.sectionsAndProducts.DTO.Subcategory.SubcategoryRequest;
import com.mazra3ty.store.sectionsAndProducts.DTO.Subcategory.SubcategoryResponse;
import com.mazra3ty.store.sectionsAndProducts.DTO.Subcategory.SubcategorySimpleResponse;
import com.mazra3ty.store.sectionsAndProducts.service.SubcategoryService;
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

@RestController
@AllArgsConstructor
@RequestMapping("/subcategory")
@Tag(name = "Subcategory", description = "CRUD REST APIs to CREATE, UPDATE, FETCH, AND DELETE Subcategory details")
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    @Operation(description = "api To Create A New subcategory")
    @PostMapping("/create")
    public ResponseEntity<SubcategorySimpleResponse> createSubcategory(@Valid @RequestBody SubcategoryRequest request) {

        return new ResponseEntity<>(subcategoryService.createSubcategory(request), HttpStatus.CREATED);
    }

    @Operation(description = "api to Get One subcategory By Id")
    @GetMapping("/get/{id}")
    public ResponseEntity<SubcategoryResponse> getSubcategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(subcategoryService.getSubcategoryById(id), HttpStatus.OK);
    }

    @Operation(description = "api to Get All subcategory")
    @GetMapping("/list")
    public ResponseEntity<List<SubcategorySimpleResponse>> getAllSubcategories() {
        return new ResponseEntity<>(subcategoryService.getAllSubcategories(), HttpStatus.OK);
    }


    @Operation(description = "api To Make Update subcategory Details By Id")
    @PutMapping("/update/{id}")
    public ResponseEntity<SubcategoryResponse> updateSubcategoryById(@PathVariable Long id, @Valid @RequestBody SubcategoryRequest request) {
        return new ResponseEntity<>(subcategoryService.updateSubcategoryById(id, request), HttpStatus.OK);
    }


    @Operation(description = "api To Make Soft Delete for subcategory By Id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        subcategoryService.softDeleteById(id);
        return ResponseEntity.ok(Map.of("Status", 200,
                "Success", true,
                "MessageAR", "تم حذف القسم الفرعي رقم " + id + " بنجاح",
                "MessageEN", "This Subcategory " + id + " Has Been Deleted",
                "Id Deleted", id,
                "Timestamp", LocalDateTime.now().toString())
        );
    }


    @Operation(description = "api to Get All Deleted subcategory")
    @GetMapping("/deleted-list")
    public ResponseEntity<List<SubcategoryResponse>> getAllDeletedSubcategories() {
        return new ResponseEntity<>(subcategoryService.getAllDeletedSubCategories(), HttpStatus.OK);
    }
}



