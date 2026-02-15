package com.mazra3ty.store.utils.exception;

import com.mazra3ty.store.sharedConstant.ErrorMassageEnum;
import com.mazra3ty.store.sharedDto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponseDto> AppErrorHandle(ApplicationException ex) {

        ErrorMassageEnum errors = ex.getErrorMassageEnum();
        ErrorResponseDto responseDto = ErrorResponseDto
                .builder()
                .key(errors.getKey())
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .messageEn(errors.getMessageEn())
                .messageAr(errors.getMessageAr())
                .timeError(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceErrorHandle(ResourceNotFoundException ex, WebRequest request) {

        ErrorMassageEnum errors = ex.getErrorMassageEnum();
        ErrorResponseDto responseDto = ErrorResponseDto
                .builder()
                .key(errors.getKey())
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .messageEn(errors.getMessageEn())
                .messageAr(errors.getMessageAr())
                .timeError(LocalDateTime.now()).build();
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralError(Exception ex) {
        // For Show Error In Terminal
        ex.printStackTrace();

        ErrorResponseDto responseDto = ErrorResponseDto.builder()
                .key("INTERNAL_SERVER_ERROR")
                .messageAr("حدث خطأ غير متوقع")
                .messageEn("An unexpected error occurred")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timeError(LocalDateTime.now()).build();

        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> ValidHandleError(MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(objectError -> {
            errors.put(objectError.getField(), objectError.getDefaultMessage());
        });

        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .key("NOT_VALID_ERROR")
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .messageEn("Validation Error")
                        .messageAr("بيانات غير صحيحة")
                        .details(errors)
                        .timeError(LocalDateTime.now()).build(), HttpStatus.BAD_REQUEST);
    }
}