package com.mazra3ty.store.validation;

import com.mazra3ty.store.Enum.ErrorMassageEnum;
import com.mazra3ty.store.entity.Category;
import com.mazra3ty.store.entity.Subcategory;
import com.mazra3ty.store.repository.CategoryRepository;
import com.mazra3ty.store.repository.SubcategoryRepository;
import com.mazra3ty.store.utils.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubcategoryValidation {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;


    public void checkUniqueName(String name) {
        if (subcategoryRepository.findByNameAndDeletedFalse(name).isPresent()) {
            throw new ApplicationException(ErrorMassageEnum.SUBCATEGORY_NAME_ALREADY_EXIST);
        }
    }

    public Category checkMainCategoryExistsAndNotDeleted(Long id) {

        return categoryRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ApplicationException(ErrorMassageEnum.CATEGORY_NOT_FOUND));
    }


    public Subcategory checkByIdAndDeletedFalse(Long id) {

        return subcategoryRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ApplicationException(ErrorMassageEnum.SUBCATEGORY_NOT_FOUND));
    }
}
