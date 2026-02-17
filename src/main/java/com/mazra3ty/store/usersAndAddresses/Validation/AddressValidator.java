package com.mazra3ty.store.usersAndAddresses.Validation;

import com.mazra3ty.store.sharedConstant.ErrorMassageEnum;
import com.mazra3ty.store.usersAndAddresses.Entity.Address;
import com.mazra3ty.store.usersAndAddresses.Repository.AddressRepository;
import com.mazra3ty.store.utils.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressValidator {
    private final AddressRepository addressRepository;

    public Address checkAddressById(Long addId) {

        return addressRepository.findByIdAndIsActiveTrue(addId)
                .orElseThrow(() -> new ApplicationException(ErrorMassageEnum.ADDRESS_NOT_FOUND));
    }
}
