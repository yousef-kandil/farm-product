package com.mazra3ty.store.DTO.Products;


import com.mazra3ty.store.Enum.UnitTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {

    @NotNull(message = "يجب تحديد اسم المنتج")
    private String name;

    private String imageUrl;

    private String description;

    @NotNull(message = "يجب تحديد نوع الوحدة")
    private UnitTypeEnum unitType;

    @NotNull(message = "يجب تحديد سعر المنتج")
    private BigDecimal price;

    @NotNull(message = "يجب اختيار القسم الرئيسي")
    private Long mainCategoryId;

    private Long subCategoryId;
}
