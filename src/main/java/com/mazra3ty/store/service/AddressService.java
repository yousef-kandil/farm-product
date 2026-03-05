package com.mazra3ty.store.service;

import com.mazra3ty.store.Enum.ErrorMassageEnum;
import com.mazra3ty.store.DTO.Address.AddressRequest;
import com.mazra3ty.store.DTO.Address.AddressResponse;
import com.mazra3ty.store.entity.Address;
import com.mazra3ty.store.entity.User;
import com.mazra3ty.store.repository.AddressRepository;
import com.mazra3ty.store.validation.AddressValidator;
import com.mazra3ty.store.validation.UserValidator;
import com.mazra3ty.store.utils.exception.ApplicationException;
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
    private final UserValidator userValidator;

    public AddressResponse createAddress(AddressRequest request) {

        User user = userValidator.checkUserById(request.getUserId());
        Address address = ObjectMapperUtils.map(request, Address.class);
        address.setUser(user);
        addressRepository.save(address);
        return ObjectMapperUtils.map(address, AddressResponse.class);
    }


    public AddressResponse getAddressById(Long id) {

        Address address = addressValidator.checkAddressById(id);
        AddressResponse response = ObjectMapperUtils.map(address, AddressResponse.class);
        response.setUserName(address.getUser().getUsername());
        return response;
    }


    public List<AddressResponse> getAllAddress() {

        List<Address> address = addressRepository.findAllByIsActiveTrue();

        return address.stream().map(add -> {
            AddressResponse response = ObjectMapperUtils.map(add, AddressResponse.class);
            response.setUserName(add.getUser().getUsername());

            return response;
        }).toList();

    }


    public AddressResponse updateAddressById(Long id, AddressRequest request) {

        Address address = addressValidator.checkAddressById(id);

        if (!address.getUser().getId().equals(request.getUserId())) {
            throw new ApplicationException(ErrorMassageEnum.ADDRESS_DOES_NOT_BELONG_TO_USER);
        }

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
