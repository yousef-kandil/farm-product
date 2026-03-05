package com.mazra3ty.store.utils.exception;

import com.mazra3ty.store.Enum.ErrorMassageEnum;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final ErrorMassageEnum errorMassageEnum;

    public ResourceNotFoundException(ErrorMassageEnum ResourceError){
        super(ResourceError.getMessageEn());
        this.errorMassageEnum = ResourceError;
    }
}
