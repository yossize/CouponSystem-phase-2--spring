package com.bhp.CouponSystem.utils;

import com.bhp.CouponSystem.exceptions.CouponSystemException;
import com.bhp.CouponSystem.exceptions.ErrMsg;

public class ExceptionUtils {

    public static Exception couponNotFound(){
        return new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS);
    }
    public static Exception companyNotFound(){
        return new CouponSystemException(ErrMsg.COMPANY_NOT_EXISTS);
    }
    public static Exception customerNotFound(){
        return new CouponSystemException(ErrMsg.CUSTOMER_BY_ID_NOT_FOUND);
    }
}
