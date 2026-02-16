package com.mazra3ty.store.sectionsAndProducts.DTO.Subcategory;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubcategoryRequest {

    @NotNull(message = "يجب إدخال الاسم اولا")
    private String name;

    private String imageUrl;

    @NotNull(message = "يجب اختيار القسم الرئيسي")
    private Long mainCategoryId;
}
