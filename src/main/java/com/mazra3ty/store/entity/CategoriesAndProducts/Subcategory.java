package com.mazra3ty.store.entity.CategoriesAndProducts;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "SUB_CATEGORY")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_CATEGORY_ID" , nullable = false)
    private Category category;

    @OneToMany(mappedBy = "subCategory",fetch = FetchType.LAZY)
    private List<Product> products;

    @Column(name = "DELETED")
    private boolean deleted = false;
}
