package com.bhp.CouponSystem.services;

import com.bhp.CouponSystem.beans.Category;
import com.bhp.CouponSystem.beans.ClientType;
import com.bhp.CouponSystem.beans.Company;
import com.bhp.CouponSystem.beans.Coupon;
import com.bhp.CouponSystem.exceptions.CompanySystemException;
import com.bhp.CouponSystem.exceptions.CouponSystemException;
import com.bhp.CouponSystem.exceptions.ErrMsg;
import com.bhp.CouponSystem.utils.ExceptionUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyServiceImpl extends ClientService implements CompanyService{

    @Override
    public boolean login(String email, String password) {
        Company c=companyRepository.findByEmail(email);
        return email.equals(c.getEmail()) && password.equals(c.getPassword());
    }

    @Override
    public void addCoupon(Coupon coupon,int companyId) throws CompanySystemException {
        Coupon c=couponRepository.findByTitle(coupon.getTitle());
        if(!(c ==null)){
            throw new CompanySystemException(ErrMsg.COUPON_TITLE_EXISTS);
        }
            couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon) throws Exception {
        Coupon c=couponRepository.findById(couponId).orElseThrow(ExceptionUtils::couponNotFound);
        if(!(c==null)){
            throw new CompanySystemException(ErrMsg.COUPON_NOT_EXISTS_FOR_UPDATE);
        }
        if(c.getCompany().getId()!=coupon.getCompany().getId()){
            throw new CompanySystemException(ErrMsg.COUPON_COMAPNY_ID_UPDATE_NOT_ALLOWED);
        }
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId,int companyId) throws CompanySystemException {
        if(!(couponRepository.existsById(couponId))){
            throw new CompanySystemException(ErrMsg.COUPON_NOT_EXISTS);
        }
        couponRepository.deleteCompanyCoupons(couponId,companyId);
        couponRepository.deleteById(couponId);

    }



    @Override
    public List<Coupon> getAllCouponsByCompanyId(int companyId) throws CompanySystemException {
        List<Coupon> companyCoupons=couponRepository.findByCompanyId(companyId);
        if(companyCoupons==null){
            throw new CompanySystemException(ErrMsg.COMPANY_HAS_NO_COUPONS);
        }
        return companyCoupons;
    }

    @Override
    public List<Coupon> getAllCouponsByCompanyAndCategory(int companyId, Category category) throws CompanySystemException {
        List<Coupon> companyCoupons=couponRepository.findByCompanyIdAndCategory(companyId,category);
        if(companyCoupons==null){
            throw new CompanySystemException(ErrMsg.COMPANY_HAS_NO_COUPONS);
        }
        return companyCoupons;
    }

    @Override
    public List<Coupon> getAllCouponsByCompanyUpToPrice(int companyId, double price) throws CompanySystemException {
        List<Coupon> companyCoupons=couponRepository.findByCompanyIdAndPriceLessThan(companyId,price);
        if(companyCoupons==null){
            throw new CompanySystemException(ErrMsg.COMPANY_HAS_NO_COUPONS);
        }
        return companyCoupons;
    }

    @Override
    public List<Coupon> getAllCouponsByMaxPrice(int companyId,float price) throws CompanySystemException {

        List<Coupon> companyCoupons=couponRepository.findByCompanyIdAndPriceLessThan(companyId,price);
        if(companyCoupons==null){
            throw new CompanySystemException(ErrMsg.COMPANY_HAS_NO_COUPONS);
        }
        return companyCoupons;
    }

    @Override
    public List<Company> getAllCompanies() throws CompanySystemException {
        List<Company> companies=companyRepository.findAll();
        if(companies==null){
            throw new CompanySystemException(ErrMsg.NO_COMPANY_FOUND);
        }
        return companies;
    }

    @Override
    public Company getCompanyById(int id) throws Exception {
        Company company=companyRepository.findById(id).orElseThrow(ExceptionUtils::couponNotFound);
        if(company==null){
            throw new CompanySystemException(ErrMsg.COMPANY_ID_NOT_EXISTS);
        }
        return company;
    }

    @Override
    public Company getCompanyByEmail(String email) throws CompanySystemException {
        Company company=companyRepository.findByEmail(email);
        if(company==null){
            throw new CompanySystemException(ErrMsg.COMPANY_ID_NOT_EXISTS);
        }
        return company;
    }
}
