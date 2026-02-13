package com.mazra3ty.store.validation;

import com.mazra3ty.store.constant.ErrorMassageEnum;
import com.mazra3ty.store.entity.CategoriesAndProducts.Product;
import com.mazra3ty.store.repository.CategoryRepository;
import com.mazra3ty.store.repository.ProductRepository;
import com.mazra3ty.store.repository.SubcategoryRepository;
import com.mazra3ty.store.utils.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductValidation {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    public void checkByNameAndDeletedFalse(String name){

        if (productRepository.existsByNameAndDeletedFalse(name)){

            throw new ApplicationException(ErrorMassageEnum.PRODUCT_NAME_ALREADY_EXIST);
        }
    }



    public Product checkProductById(Long id){

        return productRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(()-> new ApplicationException(ErrorMassageEnum.PRODUCT_NOT_FOUND));
    }



}
