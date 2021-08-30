package com.bhp.CouponSystem.services;

import com.bhp.CouponSystem.beans.ClientType;
import com.bhp.CouponSystem.beans.Company;
import com.bhp.CouponSystem.beans.Customer;
import com.bhp.CouponSystem.exceptions.CompanySystemException;
import com.bhp.CouponSystem.exceptions.CustomerSystemException;
import com.bhp.CouponSystem.repos.*;
import com.bhp.CouponSystem.exceptions.CouponSystemException;
import com.bhp.CouponSystem.exceptions.ErrMsg;
import com.bhp.CouponSystem.utils.ExceptionUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class AdminServiceImpl extends ClientService implements AdminService{
    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if (this.companyRepository.existsByEmail(company.getEmail())){
            throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_EXISTS);
        }
        if (this.companyRepository.existsByCompanyName(company.getCompanyName())){
            throw new CouponSystemException((ErrMsg.COMPANY_NAME_EXISTS));
        }
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Company company) throws CouponSystemException {
        //throw exception when company with given id is not found
        if (!this.companyRepository.existsById(company.getId())){
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_EXISTS);
        }
        //throw exception when company with given name is not found
        if (!this.companyRepository.existsByCompanyName(company.getCompanyName())){
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_NOT_EXISTS);
        }
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyId) throws  CompanySystemException {
        //throw exception when company with given id is not found
        if (!this.companyRepository.existsById(companyId)){
            throw new CompanySystemException(ErrMsg.COMPANY_NOT_EXISTS);
        }
        System.out.println("delete all coupons purchased by customers");
        couponRepository.deletePurchasedCompanyCoupons(companyId);
        System.out.println("delete comapny's coupons");
        couponRepository.deleteAllCompanyCoupons(companyId);
        System.out.println("delete company " + companyId);
        couponRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() throws CouponSystemException {

        List<Company> companies= companyRepository.findAll();
        if(companies==null){
            throw new CouponSystemException(ErrMsg.NO_COMPANIES_FOUND);
        }else
            return companies;
    }

    @Override
    public Company getSingleCompany(int id) throws Exception {

        Company c=companyRepository.findById(id).orElseThrow(ExceptionUtils::companyNotFound);;
        if(c==null){
            throw new CouponSystemException(ErrMsg.COMPANY_NOT_EXISTS);
        }else{
            return c;
        }
    }

    @Override
    public void addCustomer(Customer customer) throws CustomerSystemException {
        if (this.customerRepository.existsByEmail(customer.getEmail())){
            throw new CustomerSystemException(ErrMsg.CUSTOMER_EMAIL_EXISTS);
        }
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) throws CustomerSystemException {
        //throw exception when customer with given id is not found
        if (!this.customerRepository.existsById(customer.getId())){
            throw new CustomerSystemException(ErrMsg.CUSTOMER_ID_NOT_EXISTS);
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws CustomerSystemException {
        if (!this.customerRepository.existsById(customerId)){
            throw new CustomerSystemException(ErrMsg.CUSTOMER_ID_NOT_EXISTS);
        }
        couponRepository.deletePurchasedCustomerCoupons(customerId);
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() throws CustomerSystemException {

        List<Customer> customers=customerRepository.findAll();
        if(customers==null){
            throw new CustomerSystemException(ErrMsg.NO_CUSTOMER_FOUND);
        }else
            return customers;
    }

    @Override
    public Customer getSingleCustomer(int id) throws Exception {
        Customer c=customerRepository.findById(id).orElseThrow(ExceptionUtils::customerNotFound);
        if(c==null){
            throw new CustomerSystemException(ErrMsg.CUSTOMER_BY_ID_NOT_FOUND);
        }else {
            return c;
        }
    }

    @Override
    public boolean existsByName(String name) {
        return companyRepository.existsByCompanyName(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return existsByEmail(email);
    }


    @Override
    public  boolean login(String email, String password) throws CustomerSystemException, CompanySystemException {
        return false;
    }
}
