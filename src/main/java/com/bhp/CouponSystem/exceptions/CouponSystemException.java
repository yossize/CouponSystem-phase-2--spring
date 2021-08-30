package com.bhp.CouponSystem.exceptions;

public class CouponSystemException extends Exception{
    public CouponSystemException(ErrMsg errMsg) {
        super(errMsg.getDesc());
    }
}
