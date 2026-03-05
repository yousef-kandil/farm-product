package com.mazra3ty.store.DTO.Products;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mazra3ty.store.Enum.UnitTypeEnum;
import lombok.*;


import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private String description;

    private UnitTypeEnum unitType;

    private BigDecimal price;

    private String categoryName;

    private String subCategoryName;
}


