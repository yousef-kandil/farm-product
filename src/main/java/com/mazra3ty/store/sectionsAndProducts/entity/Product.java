package com.mazra3ty.store.sectionsAndProducts.entity;

import com.mazra3ty.store.sectionsAndProducts.ENUM.UnitTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME" , nullable = false)
    private String name;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "UNIT_TYPE" , nullable = false)
    @Enumerated(EnumType.STRING)
    private UnitTypeEnum unitType;

    @Column(name = "PRICE" , nullable = false)
    private BigDecimal price;

    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_CATEGORY_ID", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUB_CATEGORY_ID")
    private Subcategory subCategory;

    @Column(name = "DELETED")
    private boolean deleted = false;
}