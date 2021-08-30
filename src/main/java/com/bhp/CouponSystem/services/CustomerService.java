package com.bhp.CouponSystem.services;

import com.bhp.CouponSystem.beans.Category;
import com.bhp.CouponSystem.beans.Customer;
import com.bhp.CouponSystem.beans.Customer;
import com.bhp.CouponSystem.beans.Coupon;
import com.bhp.CouponSystem.exceptions.CouponSystemException;
import com.bhp.CouponSystem.exceptions.CustomerSystemException;
import com.bhp.CouponSystem.exceptions.CustomerSystemException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface CustomerService {
    void addCoupon(Coupon coupon,String customerEmail) throws Exception;
    void deleteCoupon(String couponTitle,String customerEmail) throws CustomerSystemException;
    List<Coupon> getAllCouponsByEmail(String customerEmail) throws CustomerSystemException;
    void updateCoupon(Coupon coupon,String customerEmail) throws CustomerSystemException;
    List<Coupon> getAllCouponsByMaxPrice(String customerEmail,float price) throws CustomerSystemException;
    Customer getCustomerByEmail(String customerEmail) throws CustomerSystemException;
    Customer getCustomerById(int id) throws CustomerSystemException;
    List<Customer> getAllCustomers() throws CustomerSystemException;



}
