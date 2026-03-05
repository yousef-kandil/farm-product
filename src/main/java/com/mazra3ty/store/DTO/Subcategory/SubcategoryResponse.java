package com.mazra3ty.store.DTO.Subcategory;

import com.mazra3ty.store.DTO.Products.ProductResponse;
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

    private List<ProductResponse> Products;
}
