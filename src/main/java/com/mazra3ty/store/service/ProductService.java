package com.mazra3ty.store.service;

import com.mazra3ty.store.constant.ErrorMassageEnum;
import com.mazra3ty.store.dto.categoryAndProductsDto.Products.ProductRequest;
import com.mazra3ty.store.dto.categoryAndProductsDto.Products.ProductResponse;
import com.mazra3ty.store.entity.CategoriesAndProducts.Category;
import com.mazra3ty.store.entity.CategoriesAndProducts.Product;
import com.mazra3ty.store.entity.CategoriesAndProducts.Subcategory;
import com.mazra3ty.store.repository.ProductRepository;
import com.mazra3ty.store.repository.SubcategoryRepository;
import com.mazra3ty.store.utils.exception.ApplicationException;
import com.mazra3ty.store.utils.shared.ObjectMapperUtils;
import com.mazra3ty.store.validation.CategoryValidation;
import com.mazra3ty.store.validation.ProductValidation;
import com.mazra3ty.store.validation.SubcategoryValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductValidation productValidation;
    private final CategoryValidation categoryValidation;
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryValidation subcategoryValidation;


    public ProductResponse createProduct(ProductRequest request) {

        productValidation.checkByNameAndDeletedFalse(request.getName());

        Category category = categoryValidation.checkMainCategoryExistsAndNotDeleted(request.getMainCategoryId());

        Product product = ObjectMapperUtils.map(request, Product.class);
        product.setCategory(category);
        product.setUnitType(request.getUnitType());

        if (request.getSubCategoryId() != null) {

            Subcategory subcategory = subcategoryRepository.findByIdAndDeletedFalse(request.getSubCategoryId())
                    .orElseThrow(() -> new ApplicationException(ErrorMassageEnum.SUBCATEGORY_NOT_FOUND));

            if (!subcategory.getCategory().getId().equals(category.getId())) {

                throw new ApplicationException(ErrorMassageEnum.SUBCATEGORY_NOT_BELONG_TO_CATEGORY);
            }

            product.setSubCategory(subcategory);
        } else {

            if (subcategoryRepository.existsByCategoryIdAndDeletedFalse(category.getId())) {

                throw new ApplicationException(ErrorMassageEnum.CATEGORY_HAS_SUB_CATEGORIES);
            }
        }
        productRepository.save(product);

        ProductResponse response = ObjectMapperUtils.map(product, ProductResponse.class);
        response.setCategoryName(product.getCategory().getName());
        response.setUnitType(product.getUnitType());
        if (product.getSubCategory() != null) {
            response.setSubCategoryName(product.getSubCategory().getName());
        }

        return response;
    }

    public ProductResponse getProductById(Long id) {

        Product existsProduct = productValidation.checkProductById(id);

        ProductResponse response = ObjectMapperUtils.map(existsProduct, ProductResponse.class);
        response.setCategoryName(existsProduct.getCategory().getName());
        if (existsProduct.getSubCategory() != null) {
            response.setSubCategoryName(existsProduct.getSubCategory().getName());
        }
        return response;
    }


    public ProductResponse updateProductById(Long id, ProductRequest request) {

        // 1. التأكد من وجود المنتج في الداتابيز
        Product product = productValidation.checkProductById(id);

        // 2. تحديث الاسم: فقط لو الموظف غيره، ونتأكد إن الاسم الجديد مش محجوز لمنتج تاني
        if (request.getName() != null && !request.getName().equals(product.getName())) {
            productValidation.checkByNameAndDeletedFalse(request.getName());
            product.setName(request.getName());
        }

        // 3. تحديث الأقسام (اللوجيك الخاص بالهيكلية)
        if (request.getMainCategoryId() != null) {
            // أ - لو القسم الرئيسي في الريكويست مختلف عن الحالي
            if (!product.getCategory().getId().equals(request.getMainCategoryId())) {
                Category newMainCategory = categoryValidation.checkMainCategoryExistsAndNotDeleted(request.getMainCategoryId());
                product.setCategory(newMainCategory);

                // لو نقلناه لقسم رئيسي جديد وما بعتناش قسم فرعي (حالة البيض)
                if (request.getSubCategoryId() == null) {
                    // نأكد إن القسم الرئيسي الجديد ده "فاضي" ملوش فروع تمنع إضافة منتج مباشر
                    if (subcategoryRepository.existsByCategoryIdAndDeletedFalse(newMainCategory.getId())) {
                        throw new ApplicationException(ErrorMassageEnum.CATEGORY_HAS_SUB_CATEGORIES);
                    }
                    product.setSubCategory(null); // مسح القسم الفرعي القديم
                }
            }
            // ب - لو فيه قسم فرعي مبعوث في الريكويست
            if (request.getSubCategoryId() != null) {
                // نتأكد لو المنتج ملوش فرعي أصلاً أو الفرعي الجديد مختلف عن الحالي
                if (product.getSubCategory() == null || !product.getSubCategory().getId().equals(request.getSubCategoryId())) {
                    Subcategory subcategory = subcategoryValidation.checkByIdAndDeletedFalse(request.getSubCategoryId());

                    // التأكد إن القسم الفرعي الجديد "ابن" للقسم الرئيسي الحالي للمنتج
                    if (!subcategory.getCategory().getId().equals(product.getCategory().getId())) {
                        throw new ApplicationException(ErrorMassageEnum.SUBCATEGORY_NOT_BELONG_TO_CATEGORY);
                    }
                    product.setSubCategory(subcategory);
                }
            }
        }
        if (request.getImageUrl() != null) product.setImageUrl(request.getImageUrl());
        if (request.getDescription() != null) product.setDescription(request.getDescription());
        if (request.getPrice() != null) product.setPrice(request.getPrice());
        if (request.getUnitType() != null) product.setUnitType(request.getUnitType());

        productRepository.save(product);
        ProductResponse response = ObjectMapperUtils.map(product, ProductResponse.class);
        response.setCategoryName(product.getCategory().getName());
        if (product.getSubCategory() != null) {
            response.setSubCategoryName(product.getSubCategory().getName());
        }
        return response;
    }

    public void deleteProduct(Long id) {
        Product product = productValidation.checkProductById(id);

        product.setDeleted(true);
        productRepository.save(product);
    }

}


