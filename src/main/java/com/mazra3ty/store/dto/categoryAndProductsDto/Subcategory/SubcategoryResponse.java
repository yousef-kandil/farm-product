package com.mazra3ty.store.dto.categoryAndProductsDto.Subcategory;

import com.mazra3ty.store.dto.categoryAndProductsDto.Products.ProductResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SubcategoryResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private String mainCategoryName;

    private List<ProductResponse> listOfProducts;
}
