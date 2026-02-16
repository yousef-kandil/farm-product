package com.mazra3ty.store.usersAndAddresses.Validation;

import com.mazra3ty.store.sharedConstant.ErrorMassageEnum;
import com.mazra3ty.store.usersAndAddresses.Entity.User;
import com.mazra3ty.store.usersAndAddresses.Repository.UserRepository;
import com.mazra3ty.store.utils.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public User checkUserById(Long userId) {

        return userRepository.findByIdAndIsActiveTrue(userId)
                .orElseThrow(() -> new ApplicationException(ErrorMassageEnum.USER_NOT_FOUND));
    }


    public void checkUserByEmail(String email) {

        if (userRepository.findByEmailAndIsActiveTrue(email).isPresent()) {

            throw new ApplicationException(ErrorMassageEnum.EMAIL_ALREADY_EXISTS);
        }
    }
}

