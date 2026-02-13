package com.mazra3ty.store.service;

import com.mazra3ty.store.constant.ErrorMassageEnum;
import com.mazra3ty.store.dto.categoryAndProductsDto.Products.ProductResponse;
import com.mazra3ty.store.dto.categoryAndProductsDto.Subcategory.SubcategoryRequest;
import com.mazra3ty.store.dto.categoryAndProductsDto.Subcategory.SubcategoryResponse;
import com.mazra3ty.store.dto.categoryAndProductsDto.Subcategory.SubcategorySimpleResponse;
import com.mazra3ty.store.entity.CategoriesAndProducts.Category;
import com.mazra3ty.store.entity.CategoriesAndProducts.Product;
import com.mazra3ty.store.entity.CategoriesAndProducts.Subcategory;
import com.mazra3ty.store.repository.CategoryRepository;
import com.mazra3ty.store.repository.ProductRepository;
import com.mazra3ty.store.repository.SubcategoryRepository;
import com.mazra3ty.store.utils.exception.ApplicationException;
import com.mazra3ty.store.utils.shared.ObjectMapperUtils;
import com.mazra3ty.store.validation.SubcategoryValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final SubcategoryValidation subcategoryValidation;


    public SubcategorySimpleResponse createSubcategory(SubcategoryRequest request) {

        subcategoryValidation.checkUniqueName(request.getName());
        Category checked = subcategoryValidation.checkMainCategoryExistsAndNotDeleted(request.getMainCategoryId());

        Subcategory Subcategory = new Subcategory();
        Subcategory.setName(request.getName());
        Subcategory.setImageUrl(request.getImageUrl());
        Subcategory.setCategory(checked);

        Subcategory saved = subcategoryRepository.save(Subcategory);

        SubcategorySimpleResponse response = ObjectMapperUtils.map(saved, SubcategorySimpleResponse.class);
        response.setMainCategoryName(saved.getCategory().getName());

        return response;
    }


    public SubcategoryResponse getSubcategoryById(Long id) {

        Subcategory sub = subcategoryValidation.checkByIdAndDeletedFalse(id);
        if (sub.getCategory() == null || sub.getCategory().isDeleted()) {
            throw new ApplicationException(ErrorMassageEnum.CATEGORY_NOT_FOUND);
        }

        SubcategoryResponse response = ObjectMapperUtils.map(sub, SubcategoryResponse.class);
        response.setMainCategoryName(sub.getCategory().getName());

        List<Product> productList = productRepository.findBySubCategoryIdAndDeletedFalse(id);
        if (productList != null && !productList.isEmpty()) {
            response.setListOfProducts(ObjectMapperUtils.mapAll(productList, ProductResponse.class));
        }
        return response;
    }


    public List<SubcategorySimpleResponse> getAllSubcategories() {

        return subcategoryRepository.findAllByDeletedFalse().stream().map(subcategory -> {

            SubcategorySimpleResponse response = ObjectMapperUtils.map(subcategory, SubcategorySimpleResponse.class);

            if (subcategory.getCategory() != null && !subcategory.getCategory().isDeleted()) {
                response.setMainCategoryName(subcategory.getCategory().getName());
            }
            return response;
        }).toList();
    }


    public SubcategoryResponse updateSubcategoryById(Long id, SubcategoryRequest request) {

        Subcategory subcategory = subcategoryValidation.checkByIdAndDeletedFalse(id);
        if (!subcategory.getName().equals(request.getName())) {

            subcategoryValidation.checkUniqueName(request.getName());
        }


        if (!subcategory.getCategory().getId().equals(request.getMainCategoryId())) {

            Category category = subcategoryValidation.checkMainCategoryExistsAndNotDeleted(request.getMainCategoryId());

            if (productRepository.existsByCategoryIdAndDeletedFalse(category.getId())) {
                throw new ApplicationException(ErrorMassageEnum.CATEGORY_HAS_PRODUCTS);
            }
            subcategory.setCategory(category);
        }


        subcategory.setName(request.getName());
        subcategory.setImageUrl(request.getImageUrl());

        Subcategory saved = subcategoryRepository.save(subcategory);

        SubcategoryResponse response = ObjectMapperUtils.map(saved, SubcategoryResponse.class);
        response.setMainCategoryName(saved.getCategory().getName());
        return response;
    }


    public void softDeleteById(Long id) {

        Subcategory subcategory = subcategoryValidation.checkByIdAndDeletedFalse(id);

        if (subcategory.getProducts() != null && !subcategory.getProducts().isEmpty()) {

            throw new ApplicationException(ErrorMassageEnum.SUBCATEGORY_HAS_PRODUCTS);
        } else {

            subcategory.setDeleted(true);
            subcategoryRepository.save(subcategory);
        }
    }


    public List<SubcategoryResponse> getAllDeletedSubCategories() {

        return subcategoryRepository.findAllByDeletedTrue().stream().map(subcategory -> {

            return ObjectMapperUtils.map(subcategory, SubcategoryResponse.class);
        }).toList();
    }
}