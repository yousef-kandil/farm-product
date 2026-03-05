package com.mazra3ty.store.DTO.Address;

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
public class AddressResponse {

    private Long id;

    private String city;

    private String area;

    private String streetName;

    private String buildingNo;

    private String floorNo;

    private String apartmentNo;

    private String landmark;

    private String addressDescription;

    private boolean isDefault;

    private boolean isActive;

    private String userName;
}
