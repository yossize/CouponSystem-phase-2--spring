package com.bhp.CouponSystem.exceptions;

public class CompanySystemException extends Exception{
    public CompanySystemException(ErrMsg errMsg) {
        super(errMsg.getDesc());
    }
}
