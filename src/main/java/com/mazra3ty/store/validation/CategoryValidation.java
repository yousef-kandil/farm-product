package com.mazra3ty.store.validation;

import com.mazra3ty.store.constant.ErrorMassageEnum;
import com.mazra3ty.store.entity.CategoriesAndProducts.Category;
import com.mazra3ty.store.repository.CategoryRepository;
import com.mazra3ty.store.utils.exception.ApplicationException;
import com.mazra3ty.store.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CategoryValidation {
    private final CategoryRepository categoryRepository;

    public void checkUniqueName(String name) {
        if (categoryRepository.findByName(name).isPresent()) {
            throw new ApplicationException(ErrorMassageEnum.CATEGORY_NAME_ALREADY_EXIST);
        }
    }

    public Category checkMainCategoryExistsAndNotDeleted(Long id) {

        return categoryRepository.findByIdAndDeletedFalse(id)
                 .orElseThrow(() -> new ApplicationException(ErrorMassageEnum.CATEGORY_NOT_FOUND));
    }

    public void validateBeforeDelete(Long id) {
        // 1. نجيب القسم (أو نضرب Error لو مش موجود أصلاً)
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMassageEnum.CATEGORY_NOT_FOUND));

        // 2. لو القسم ممسوح أصلاً، نعتبره "غير موجود" بالنسبة لعملية الحذف
        if (category.isDeleted()) {
            throw new ResourceNotFoundException(ErrorMassageEnum.CATEGORY_NOT_FOUND);
        }

        // 3. نمنع المسح فقط لو فيه أقسام فرعية "نشطة" (ليست ممسوحة)
        boolean hasActiveSubCategories = category.getSubCategory().stream()
                .anyMatch(sub -> !sub.isDeleted()); // بنشوف لو فيه واحد بس مش ممسوح

        if (hasActiveSubCategories) {
            throw new ApplicationException(ErrorMassageEnum.CATEGORY_HAS_SUB_CATEGORIES);
        }

        // 4. نمنع المسح لو فيه منتجات (بافتراض إن المنتج ملوش Soft Delete حالياً أو تتبع نفس اللوجيك)
        // محتاج نغيرها لما نعمل للمنتح ديليت
        if (category.getProduct() != null && !category.getProduct().isEmpty()) {
            throw new ApplicationException(ErrorMassageEnum.CATEGORY_HAS_PRODUCTS);
        }
    }


}
