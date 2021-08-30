package com.bhp.CouponSystem.services;

import com.bhp.CouponSystem.beans.Category;
import com.bhp.CouponSystem.beans.Company;
import com.bhp.CouponSystem.beans.Coupon;
import com.bhp.CouponSystem.beans.Customer;
import com.bhp.CouponSystem.exceptions.CompanySystemException;
import com.bhp.CouponSystem.exceptions.CouponSystemException;
import com.bhp.CouponSystem.exceptions.CustomerSystemException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface CompanyService {
    void addCoupon(Coupon coupon,int companyId) throws CompanySystemException;
    void updateCoupon(int couponId ,Coupon coupon) throws Exception;

    List<Coupon> getAllCouponsByCompanyId(int companyId) throws CompanySystemException;
    List<Coupon> getAllCouponsByCompanyAndCategory(int companyId, Category category) throws CompanySystemException;
    List<Coupon> getAllCouponsByCompanyUpToPrice(int companyId, double price) throws CompanySystemException;
    List<Coupon> getAllCouponsByMaxPrice(int companyId,float price) throws CompanySystemException;
    List<Company> getAllCompanies() throws CompanySystemException;
    Company getCompanyById(int id) throws Exception;
    Company getCompanyByEmail(String  email) throws CompanySystemException;
    void deleteCoupon(int couponId,int companyId) throws CompanySystemException;

}
