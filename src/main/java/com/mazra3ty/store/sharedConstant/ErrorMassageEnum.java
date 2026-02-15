package com.mazra3ty.store.sharedConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMassageEnum {

    //Standard Errors
    ERROR_USER_LOCKED("USER_LOCKED", "This user is locked", "هذا الحساب غير مفعل"),
    ERROR_USER_SUSPENDED("USER_SUSPENDED", "This user is suspended", "هذا الحساب معطل"),
    ERROR_NO_ROLE_FOUND("ERROR_NO_RULE_FOUND", "No role found", "No role found"),
    ERROR_NOT_LOGGED_IN("ERROR_AUTHENTICATION_FAILED", "Please login first", "برجاء تسجيل الدخول أولا"),
    ERROR_PASSWORD_USERID("ERROR_PASSWORD_USERID", "New password can't be the same user email", "لا يمكن ان يكون الرقم السرى نفس البريد الالكترونى للمستخدم"),
    ERROR_NO_TOKEN_SUPPORTED("ERROR_AUTHENTICATION_FAILED", "Token not provided", "لا يوجد توكن"),
    ERROR_AUTHENTICATION_FAILED("ERROR_AUTHENTICATION_FAILED", "Username or password not correct", "اسم المستخدم او الرقم السرى غير صحيح"),
    ERROR_UNAUTHORIZED("ERROR_UNAUTHORIZED", "You are not authorized to do this action", "انت غير مصرح لك بالقيام بهذا الاجراء"),
    ERROR_WRONG_OTP("ERROR_WRONG_OTP", "OTP not correct", "OTP غير صحيح"),
    ERROR_OTP_EXPIRED("ERROR_OTP_EXPIRED", "OTP expired", "OTP منتهى الصلاحية"),
    ERROR_EMAIL_ALREADY_EXIST("EMAIL_ALREADY_EXIST", "Email already exist", "هذا الايميل موجود بالفعل"),


    // Category Error Message
    CATEGORY_NAME_ALREADY_EXIST("CATEGORY_NAME_ALREADY_EXIST", "this category already exist", "اسم القسم موجود بالفعل"),
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", "this category not found", "هذا القسم غير موجود"),
    CATEGORY_HAS_PRODUCTS("CATEGORY_HAS_PRODUCTS", "this category contains products that cannot be deleted", "لايمكن حذف هذا القسم لانه يحتوي علي منتجات "),
    CATEGORY_HAS_SUB_CATEGORIES("CATEGORY_HAS_SUB_CATEGORIES", "Cannot delete category because it contains sub-categories", "لا يمكن حذف القسم لأنه يحتوي على أقسام فرعية"),

    // Subcategory Error Message
    SUBCATEGORY_NAME_ALREADY_EXIST("SUBCATEGORY_NAME_ALREADY_EXIST", "this subcategory already exist", "اسم القسم الفرعي موجود بالفعل"),
    SUBCATEGORY_HAS_PRODUCTS("SUBCATEGORY_HAS_PRODUCTS", "this Subcategory contains products that cannot be deleted", "لايمكن حذف هذا القسم الفرعي لانه يحتوي علي منتجات "),
    SUBCATEGORY_NOT_FOUND("SUBCATEGORY_NOT_FOUND", "this Subcategory not found", "هذا القسم الفرعي غير موجود"),

    //Product Error Message
    PRODUCT_NAME_ALREADY_EXIST("PRODUCT_NAME_ALREADY_EXIST", "this Product already exist", "اسم المنتج موجود بالفعل"),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", "this Product not found", "هذا المنتج غير موجود"),
    SUBCATEGORY_NOT_BELONG_TO_CATEGORY("SUBCATEGORY_NOT_BELONG_TO_CATEGORY", "Subcategory does not belong to selected category", "القسم الفرعي لا يتبع هذا القسم الرئيسي"),

    ;

    private final String key;

    private final String messageEn;

    private final String messageAr;
}
