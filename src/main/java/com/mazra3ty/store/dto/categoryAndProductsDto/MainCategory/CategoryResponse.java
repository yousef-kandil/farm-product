package com.mazra3ty.store.dto.categoryAndProductsDto.MainCategory;

import com.mazra3ty.store.dto.categoryAndProductsDto.Products.ProductResponse;
import com.mazra3ty.store.dto.categoryAndProductsDto.Subcategory.SubcategoryResponse;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private List<SubcategoryResponse> Subcategory;

    private List<ProductResponse> product;
}
