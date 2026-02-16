package com.mazra3ty.store.usersAndAddresses.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {

    private Long id;

    private String fullName;

    private String username;

    private String phone;

    private String email;

    private boolean isActive;

    private LocalDateTime createdAt;
}
