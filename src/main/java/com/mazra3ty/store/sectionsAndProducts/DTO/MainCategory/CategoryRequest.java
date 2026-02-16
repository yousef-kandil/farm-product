package com.mazra3ty.store.sectionsAndProducts.DTO.MainCategory;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryRequest {

    @NotNull(message = "يجب إدخال اسم القسم")
    @Size(min = 2, max = 150, message = "الاسم يجب ان يتكون من 2 حرف الي 150 حرف")
    private String name;

    private String imageUrl;
}
