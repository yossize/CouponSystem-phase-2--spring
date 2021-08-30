package com.bhp.CouponSystem.services;

import com.bhp.CouponSystem.beans.Company;
import com.bhp.CouponSystem.beans.Customer;
import com.bhp.CouponSystem.exceptions.CompanySystemException;
import com.bhp.CouponSystem.exceptions.CouponSystemException;
import com.bhp.CouponSystem.exceptions.CustomerSystemException;

import java.util.List;

public interface AdminService {
    void addCompany(Company company) throws CouponSystemException;
    void updateCompany(Company company) throws CouponSystemException;
    void deleteCompany(int companyId) throws CouponSystemException, CompanySystemException;

    List<Company> getAllCompanies() throws CouponSystemException;
    Company getSingleCompany(int id) throws Exception;

    void addCustomer(Customer customer) throws CouponSystemException, CustomerSystemException;
    void updateCustomer(Customer customer) throws CustomerSystemException;
    void deleteCustomer(int customerId) throws CustomerSystemException;

    List<Customer> getAllCustomers() throws CustomerSystemException;
    Customer getSingleCustomer(int id) throws Exception;


    boolean existsByName(String name);
    boolean existsByEmail(String email);
}