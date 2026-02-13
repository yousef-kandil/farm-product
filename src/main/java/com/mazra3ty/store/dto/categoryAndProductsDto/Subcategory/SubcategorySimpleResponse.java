package com.mazra3ty.store.dto.categoryAndProductsDto.Subcategory;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubcategorySimpleResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private String mainCategoryName;
}
