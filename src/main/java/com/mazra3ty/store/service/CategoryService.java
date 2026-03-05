package com.mazra3ty.store.service;

import com.mazra3ty.store.Enum.ErrorMassageEnum;
import com.mazra3ty.store.DTO.MainCategory.CategorySimpleResponse;
import com.mazra3ty.store.DTO.MainCategory.CategoryRequest;
import com.mazra3ty.store.DTO.MainCategory.CategoryResponse;
import com.mazra3ty.store.DTO.Products.ProductResponse;
import com.mazra3ty.store.DTO.Subcategory.SubcategoryResponse;
import com.mazra3ty.store.entity.Category;
import com.mazra3ty.store.entity.Product;
import com.mazra3ty.store.entity.Subcategory;
import com.mazra3ty.store.repository.CategoryRepository;
import com.mazra3ty.store.repository.ProductRepository;
import com.mazra3ty.store.repository.SubcategoryRepository;
import com.mazra3ty.store.utils.shared.ObjectMapperUtils;
import com.mazra3ty.store.utils.exception.ApplicationException;
import com.mazra3ty.store.validation.CategoryValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryValidation categoryValidation;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;

    public CategorySimpleResponse createCategory(CategoryRequest request) {

        categoryValidation.checkUniqueName(request.getName());

        Category category = Category
                .builder()
                .name(request.getName())
                .imageUrl(request.getImageUrl())
                .build();

        categoryRepository.save(category);

        CategorySimpleResponse response = ObjectMapperUtils.map(category, CategorySimpleResponse.class);
        response.setHasProductFlag(false);
        response.setHasSubcategoryFlag(false);

        return response;
    }

    public CategoryResponse getCategoryById(Long id) {
        // 1. هات القسم الرئيسي وتأكد إنه مش ممسوح
        Category category = categoryRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ApplicationException(ErrorMassageEnum.CATEGORY_NOT_FOUND));

        CategoryResponse response = ObjectMapperUtils.map(category, CategoryResponse.class);

        // 2. اسأل عن الأقسام الفرعية "الحية" (ليست Soft Deleted)
        if (subcategoryRepository.existsByCategoryIdAndDeletedFalse(id)) {
            List<Subcategory> activeSubs = subcategoryRepository.findAllByCategoryIdAndDeletedFalse(id);
            response.setSubcategory(ObjectMapperUtils.mapAll(activeSubs, SubcategoryResponse.class));
        }
        // 3. لو مفيش أقسام فرعية، اسأل عن المنتجات "الحية"
        else if (productRepository.existsByCategoryIdAndDeletedFalse(id)) {
            List<Product> activeProducts = productRepository.findAllByCategoryIdAndDeletedFalse(id);
            response.setProduct(ObjectMapperUtils.mapAll(activeProducts, ProductResponse.class));
        }

        return response;
    }


    public List<CategorySimpleResponse> getAllCategories() {

        return categoryRepository.findAllByDeletedFalse().stream().map(category -> {

            CategorySimpleResponse response = ObjectMapperUtils.map(category, CategorySimpleResponse.class);

            // 1. اسأل الريبوزيتوري: هل فيه أقسام فرعية "حية"؟
            if (subcategoryRepository.existsByCategoryIdAndDeletedFalse(category.getId())) {
                response.setHasSubcategoryFlag(true);
            }
            // 2. لو مفيش، اسأل: هل فيه منتجات "حية"؟
            else if (productRepository.existsByCategoryIdAndDeletedFalse(category.getId())) {
                response.setHasProductFlag(true);
            }

            return response;
        }).toList();

    }


    public CategoryResponse updateCategoryById(Long id, CategoryRequest request) {

        Category category = categoryRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ApplicationException(ErrorMassageEnum.CATEGORY_NOT_FOUND));

        if (!category.getName().equals(request.getName())) {

            categoryValidation.checkUniqueName(request.getName());
        }

        ObjectMapperUtils.map(request, category);
        categoryRepository.save(category);
        return ObjectMapperUtils.map(category, CategoryResponse.class);
    }

    public void softDeleteById(Long id) {

        categoryValidation.validateBeforeDelete(id);

        Category categories = categoryRepository.findByIdAndDeletedFalse(id).get();
        categories.setDeleted(true);
        categoryRepository.save(categories);
    }


    public List<CategoryResponse> getAllDeletedCategories() {

        List<Category> allCategory = categoryRepository.findAllByDeletedTrue();
        return ObjectMapperUtils.mapAll(allCategory, CategoryResponse.class);
    }

}
