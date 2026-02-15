package com.mazra3ty.store.sectionsAndProducts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "MAIN_CATEGORIES")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME" , nullable = false)
    @Size(min = 2, max = 150, message = "الاسم يجب ان يتكون من 2 حرف الي 150 حرف")
    private String name;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Subcategory> subCategory;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> product;

    @Column(name = "DELETED")
    private boolean deleted = false;
}
