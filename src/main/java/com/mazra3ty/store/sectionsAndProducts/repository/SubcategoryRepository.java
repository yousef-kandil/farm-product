package com.mazra3ty.store.sectionsAndProducts.repository;

import com.mazra3ty.store.sectionsAndProducts.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    Optional<Subcategory> findByNameAndDeletedFalse(String name);

    Optional<Subcategory> findByIdAndDeletedFalse(Long id);

    List<Subcategory> findAllByDeletedFalse();

    List<Subcategory> findAllByDeletedTrue();

    boolean existsByCategoryIdAndDeletedFalse(Long categoryId);

    List<Subcategory> findAllByCategoryIdAndDeletedFalse(Long categoryId);


}
