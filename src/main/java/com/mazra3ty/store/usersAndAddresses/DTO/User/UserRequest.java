package com.mazra3ty.store.usersAndAddresses.DTO.User;

import com.mazra3ty.store.usersAndAddresses.DTO.Address.AddressRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    @NotBlank(message = "يجب تحديد الاسم")
    private String fullName;

    @NotBlank(message = "يجب تحديد اسم المستخدم")
    private String username;

    @NotBlank(message = "يجب تحديد رقم الهاتف")
    private String phone;

    @NotBlank(message = "يجب تحديد الإيميل الخاص بكم")
    @Email(message = "يجب إدخال إيميل صحيح")
    private String email;

    @NotBlank(message = "يجب تحديد كلمة المرور")
    private String password;

    @NotNull(message = "يجب إدخال عنوان واحد علي الاقل")
    private List<AddressRequest> address;
}
