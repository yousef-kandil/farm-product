package com.mazra3ty.store.validation;

import com.mazra3ty.store.Enum.ErrorMassageEnum;
import com.mazra3ty.store.entity.Address;
import com.mazra3ty.store.repository.AddressRepository;
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
