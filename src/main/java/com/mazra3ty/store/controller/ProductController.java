package com.mazra3ty.store.controller;

import com.mazra3ty.store.DTO.Products.ProductRequest;
import com.mazra3ty.store.DTO.Products.ProductResponse;
import com.mazra3ty.store.service.ProductService;
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

@Tag(name = "Product Controller", description = "CRUD REST APIs to CREATE, UPDATE, FETCH, AND DELETE Product details")
@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {

        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);

    }

    @Operation(description = "api to Get One Product By Id")
    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @Operation(description = "api to Get All Product")
    @GetMapping("/list")
    public ResponseEntity<List<ProductResponse>> getProductById() {
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @Operation(description = "api To Make Update Product Details By Id")
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProductById(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return new ResponseEntity<>(productService.updateProductById(id, request), HttpStatus.OK);
    }

    @Operation(description = "api To Make Soft Delete for Product By Id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(Map.of("Status", 200,
                "Success", true,
                "MessageAR", "تم حذف المنتج رقم " + id + " بنجاح",
                "MessageEN", "This Product " + id + " Has Been Deleted",
                "Id Deleted", id,
                "Timestamp", LocalDateTime.now().toString())
        );
    }
}