package com.mazra3ty.store.utils.exception;

import com.mazra3ty.store.constant.ErrorMassageEnum;
import lombok.Getter;


@Getter

public class ApplicationException extends RuntimeException {
    private final ErrorMassageEnum errorMassageEnum;

    public ApplicationException(ErrorMassageEnum errorMassageEnum) {
        super(errorMassageEnum.getMessageEn());
        this.errorMassageEnum = errorMassageEnum;
    }

}
