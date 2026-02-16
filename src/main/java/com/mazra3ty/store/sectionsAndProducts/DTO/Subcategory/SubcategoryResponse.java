package com.mazra3ty.store.sectionsAndProducts.DTO.Subcategory;

import com.mazra3ty.store.sectionsAndProducts.DTO.Products.ProductResponse;
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
