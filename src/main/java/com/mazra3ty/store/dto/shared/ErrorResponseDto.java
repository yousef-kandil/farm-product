package com.mazra3ty.store.dto.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto {

    private String key;

    private int httpStatus;

    private String messageEn;

    private String messageAr;

    private LocalDateTime timeError;

    private Object details;
}
