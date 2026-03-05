package com.mazra3ty.store.controller;

import com.mazra3ty.store.DTO.Address.AddressRequest;
import com.mazra3ty.store.DTO.Address.AddressResponse;
import com.mazra3ty.store.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/address")
@Tag(name = "Address", description = "CRUD REST APIs to CREATE, UPDATE, FETCH, AND DELETE Address details")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/create")
    @Operation(summary = "api To Create New Address", description = "api To Create New Address")
    public ResponseEntity<AddressResponse> createAddress(@Valid @RequestBody AddressRequest request) {

        return new ResponseEntity<>(addressService.createAddress(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "api To Get Address By Id", description = "api To Get Address By Id")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable Long id) {
        return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    @Operation(summary = "api To Get All Address By Id", description = "api To Get All Address By Id")
    public ResponseEntity<List<AddressResponse>> getAllAddress() {
        return new ResponseEntity<>(addressService.getAllAddress(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "api To Update Address By Id", description = "api To Update Address By Id")
    public ResponseEntity<AddressResponse> updateById(@PathVariable Long id, @RequestBody AddressRequest request) {
        return new ResponseEntity<>(addressService.updateAddressById(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "api To Delete Address By Id", description = "api To Delete Address By Id")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        addressService.SoftDeleteById(id);
        return ResponseEntity.ok(Map.of(
                "Status", 200,
                "Success", true,
                "MessageAR", "تم حذف العنوان رقم " + id + " بنجاح",
                "MessageEN", "This Address " + id + " Has Been Deleted",
                "Id Deleted", id,
                "Timestamp", LocalDateTime.now().toString())
        );
    }
}
