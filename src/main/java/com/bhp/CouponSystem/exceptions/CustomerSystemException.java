package com.bhp.CouponSystem.exceptions;

public class CustomerSystemException extends Exception{
    public CustomerSystemException(ErrMsg errMsg) {
        super(errMsg.getDesc());
    }
}
