package com.mazra3ty.store.usersAndAddresses.Controller;

import com.mazra3ty.store.usersAndAddresses.DTO.User.UserRequest;
import com.mazra3ty.store.usersAndAddresses.DTO.User.UserResponse;
import com.mazra3ty.store.usersAndAddresses.Service.UserService;
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
@RequestMapping("/user")
@Tag(name = "User", description = "CRUD REST APIs to CREATE, UPDATE, FETCH, AND DELETE User details")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    @Operation(description = "api To Create New User")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {

        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(description = "api To Get User By Id")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    @Operation(description = "api To Get All Users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @Operation(description = "api To Update User By Id")
    public ResponseEntity<UserResponse> updateById(@PathVariable Long id, @Valid @RequestBody UserRequest request) {

        return new ResponseEntity<>(userService.updateUserById(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "api To Soft Delete User By Id")
    public ResponseEntity<?> SoftDelete(@PathVariable Long id) {
        userService.userSoftDeleteById(id);
        return ResponseEntity.ok(Map.of("Status", 200,
                "Success", true,
                "MessageAR", "تم حذف المستخدم رقم " + id + " بنجاح",
                "MessageEN", "This User " + id + " Has Been Deleted",
                "Id Deleted", id,
                "Timestamp", LocalDateTime.now().toString())
        );
    }


}
