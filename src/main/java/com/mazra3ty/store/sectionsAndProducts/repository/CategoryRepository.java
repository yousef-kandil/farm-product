package com.mazra3ty.store.sectionsAndProducts.repository;

import com.mazra3ty.store.sectionsAndProducts.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Optional<Category> findByName(String name);

    List<Category> findAllByDeletedFalse();

    List<Category> findAllByDeletedTrue();

    Optional<Category> findByIdAndDeletedFalse(Long id);
}
