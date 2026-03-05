package com.mazra3ty.store.DTO.MainCategory;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategorySimpleResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private boolean hasSubcategoryFlag;

    private boolean hasProductFlag;
}
