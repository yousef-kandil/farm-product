package com.mazra3ty.store.controller;

import com.mazra3ty.store.DTO.AboutUs.AboutUsRequest;
import com.mazra3ty.store.DTO.AboutUs.AboutUsResponse;
import com.mazra3ty.store.service.AboutUsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/about-us")
@Tag(name = "About Us", description = "CRUD REST APIs to CREATE, UPDATE, FETCH, About Us details")
public class AboutUsController {

    private final AboutUsService aboutUsService;

    @PostMapping("/create")
    @Operation(summary = "api To Create New About Us", description = "api To Create New About Us")
    public ResponseEntity<AboutUsResponse> createAboutUs(@Valid @RequestBody AboutUsRequest request) {

        return new ResponseEntity<>(aboutUsService.createAboutUs(request), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @Operation(summary = "api To Get About Us By Id", description = "api To Get About Us By Id")
    public ResponseEntity<AboutUsResponse> getAboutUsById(@PathVariable Long id) {
        return new ResponseEntity<>(aboutUsService.getAboutUsById(id), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    @Operation(summary = "api To Update About Us By Id", description = "api To Update About Us By Id")
    public ResponseEntity<AboutUsResponse> updateById(@PathVariable Long id, @RequestBody AboutUsRequest request) {
        return new ResponseEntity<>(aboutUsService.updateAboutUsById(id, request), HttpStatus.OK);
    }
}
