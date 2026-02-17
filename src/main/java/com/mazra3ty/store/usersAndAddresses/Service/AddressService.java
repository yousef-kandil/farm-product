package com.mazra3ty.store.usersAndAddresses.Service;

import com.mazra3ty.store.usersAndAddresses.DTO.Address.AddressRequest;
import com.mazra3ty.store.usersAndAddresses.DTO.Address.AddressResponse;
import com.mazra3ty.store.usersAndAddresses.Entity.Address;
import com.mazra3ty.store.usersAndAddresses.Repository.AddressRepository;
import com.mazra3ty.store.usersAndAddresses.Validation.AddressValidator;
import com.mazra3ty.store.utils.shared.ObjectMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressValidator addressValidator;

    public AddressResponse createAddress(AddressRequest request) {
        Address address = ObjectMapperUtils.map(request, Address.class);
        addressRepository.save(address);
        return ObjectMapperUtils.map(address, AddressResponse.class);
    }


    public AddressResponse getAddressById(Long id) {

        Address address = addressValidator.checkAddressById(id);
        return ObjectMapperUtils.map(address, AddressResponse.class);
    }


    public List<AddressResponse> getAllAddress() {

        List<Address> address = addressRepository.findAllByIsActiveTrue();
        return ObjectMapperUtils.mapAll(address, AddressResponse.class);
    }


    public AddressResponse updateAddressById(Long id, AddressRequest request) {

        Address address = addressValidator.checkAddressById(id);

        if (request.getCity() != null && !address.getCity().equals(request.getCity())) {
            address.setCity(request.getCity());
        }
        if (request.getArea() != null && !address.getArea().equals(request.getArea())) {
            address.setArea(request.getArea());
        }
        if (request.getStreetName() != null && !address.getStreetName().equals(request.getStreetName())) {
            address.setStreetName(request.getStreetName());
        }
        if (request.getBuildingNo() != null && !address.getBuildingNo().equals(request.getBuildingNo())) {
            address.setBuildingNo(request.getBuildingNo());
        }
        if (request.getFloorNo() != null && !address.getFloorNo().equals(request.getFloorNo())) {
            address.setFloorNo(request.getFloorNo());
        }
        if (request.getApartmentNo() != null && !address.getApartmentNo().equals(request.getApartmentNo())) {
            address.setApartmentNo(request.getApartmentNo());
        }
        if (request.getLandmark() != null && !address.getLandmark().equals(request.getLandmark())) {
            address.setLandmark(request.getLandmark());
        }
        if (request.getAddressDescription() != null && !address.getAddressDescription().equals(request.getAddressDescription())) {
            address.setAddressDescription(request.getAddressDescription());
        }

        addressRepository.save(address);
        return ObjectMapperUtils.map(address, AddressResponse.class);
    }


    public void SoftDeleteById(Long id) {
        Address address = addressValidator.checkAddressById(id);
        address.setActive(false);
        addressRepository.save(address);
    }

}
