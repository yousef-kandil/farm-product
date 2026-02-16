package com.mazra3ty.store.usersAndAddresses.DTO.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    @NotNull(message = "يجب تحديد الاسم")
    private String fullName;

    @NotNull(message = "يجب تحديد اسم المستخدم")
    private String username;

    @NotNull(message = "يجب تحديد رقم الهاتف")
    private String phone;

    @NotNull(message = "يجب تحديد الإيميل الخاص بكم")
    @Email(message = "يجب إدخال إيميل صحيح")
    private String email;

    @NotNull(message = "يجب تحديد كلمة المرور")
    private String password;
}
