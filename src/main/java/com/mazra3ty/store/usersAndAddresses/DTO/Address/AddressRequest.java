package com.mazra3ty.store.usersAndAddresses.DTO.Address;

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
public class AddressRequest {

    @NotNull(message = "يجب إدخال اسم المدينة")
    private String city;

    @NotNull(message = "يجب إدخال اسم المنطقة")
    private String area;

    @NotNull(message = "يجب إدخال اسم الشارع")
    private String streetName;

    private String buildingNo;

    private String floorNo;

    private String apartmentNo;

    @NotNull(message = "يجب إدخال علامة مميزة قرب عنوانك")
    private String landmark;

    @NotNull(message = "يرجي تحديد نوع العنوان مثال/ المنزل / العمل / المقهي")
    private String addressDescription;

    private boolean isDefault;
}
