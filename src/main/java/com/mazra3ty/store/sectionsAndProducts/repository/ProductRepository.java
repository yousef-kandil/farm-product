package com.mazra3ty.store.sectionsAndProducts.repository;

import com.mazra3ty.store.sectionsAndProducts.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {

    boolean existsByNameAndDeletedFalse(String name);

    boolean existsByCategoryIdAndDeletedFalse(Long categoryId);

    Optional<Product> findByIdAndDeletedFalse(Long id);

    List<Product> findAllByCategoryIdAndDeletedFalse(Long categoryId);

    List<Product> findBySubCategoryIdAndDeletedFalse(Long subId);


}
