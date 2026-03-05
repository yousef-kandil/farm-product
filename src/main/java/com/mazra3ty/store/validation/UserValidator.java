package com.mazra3ty.store.validation;

import com.mazra3ty.store.Enum.ErrorMassageEnum;
import com.mazra3ty.store.entity.User;
import com.mazra3ty.store.repository.UserRepository;
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

