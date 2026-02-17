package com.mazra3ty.store.usersAndAddresses.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "AREA")
    private String area;

    @Column(name = "STREET_NAME")
    private String streetName;

    @Column(name = "BUILDING_NO")
    private String buildingNo;

    @Column(name = "FLOOR_NO")
    private String floorNo;

    @Column(name = "APARTMENT_NO")
    private String apartmentNo;

    @Column(name = "LAND_MARK")
    private String landmark;

    @Column(name = "ADDRESS_DESCRIPTION")
    private String addressDescription;

    @Column(name = "IS_DEFAULT")
    private boolean isDefault = false;

    @Column(name = "IS_ACTIVE")
    private boolean isActive = true;
}
