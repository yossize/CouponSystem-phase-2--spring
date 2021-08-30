package com.bhp.CouponSystem.exceptions;

public enum ErrMsg {
    COMPANY_EMAIL_EXISTS("Company email is already exists"),
    COMPANY_NAME_EXISTS("Company name is already exists"),
    COMPANY_ID_NOT_EXISTS("Company with this id not exists"),
    COMPANY_ID_EXISTS("Company with this id exists"),
    COMPANY_NAME_NOT_EXISTS("Company with this name not exists"),
    NO_COMPANIES_FOUND("no companies exists"),
    COMPANY_NOT_EXISTS("company not exists"),
    COMPANY_WRONG_PASSWORD("company password is incorrect"),
    NO_COMPANY_FOUND("supplied email for company not exists"),

    CUSTOMER_EMAIL_EXISTS("Customer email is already exists"),
    NO_CUSTOMER_FOUND("no customer exists"),
    CUSTOMER_BY_ID_NOT_FOUND("customer with given id not found"),
    CUSTOMER_COUPON_ALREADY_EXISTS("customer already purchased this coupon"),
    CUSTOMER_ID_NOT_EXISTS("Customer id not exists"),
    CUSTOMER_PURCHASE_COUPON_ZERO_AMOUNT("coupon can not be purchased. the amount is zero"),
    CUSTOMER_HAS_NO_COUPONS("the customer has no coupns"),
    CUSTOMER_HAS_NO_COUPONS_FOR_THIS_CATEGORY("the customer has no coupns for this category"),
    CUSTOMER_PURCHASE_COUPON_IS_EXPIRED("coupon can not be purchased. the coupon expired"),
    CUSTOMER_EMAIL_NOT_EXISTS("customer email does not exists"),
    CUSTOMER_WRONG_PASSWORD("customer password is incorrect"),


    COUPON_EXISTS_FOR_COMPANY("Coupon can be added. it already exists"),
    COUPON_TITLE_EXISTS("Coupon can not be added. title already exists"),
    COUPON_NOT_EXISTS("Coupon can be deleted. it doesn't exists"),
    COUPON_COMAPNY_ID_UPDATE_NOT_ALLOWED("can not update coupn's company id"),
    COMPANY_HAS_NO_COUPONS("There are no coupons for this company"),
    COUPON_NOT_EXISTS_FOR_UPDATE("Coupon can not be updated. the id not exists");
    private String desc;



    ErrMsg(String desc) {
        this.desc=desc;
    }
    public String getDesc() {
        return desc;
    }
}
