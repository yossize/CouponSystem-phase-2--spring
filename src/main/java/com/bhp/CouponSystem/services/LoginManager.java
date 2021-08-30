package com.bhp.CouponSystem.services;

import com.bhp.CouponSystem.beans.ClientType;
import com.bhp.CouponSystem.beans.Company;
import com.bhp.CouponSystem.beans.Customer;
import com.bhp.CouponSystem.exceptions.CompanySystemException;
import com.bhp.CouponSystem.exceptions.CustomerSystemException;
import com.bhp.CouponSystem.exceptions.ErrMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {
    @Autowired
    private ApplicationContext ctx;

    public ClientService login(String email, String password, ClientType clientType) throws Exception {
        if (clientType.name().equals("ADMINISTRATOR")) {
            if(email.equals("admin@admin.com")&&(password.equals("admin"))) {
                AdminService adminService = ctx.getBean(AdminService.class);
                return (ClientService) adminService;
            }else {
                throw new Exception ("Admin can not be login");
            }
        }
        if (clientType.name().equals("CUSTOMER")) {
            CustomerService customerService = ctx.getBean(CustomerService.class);
            Customer cust=customerService.getCustomerByEmail(email);
            if (cust==null){
                throw new CustomerSystemException(ErrMsg.CUSTOMER_EMAIL_NOT_EXISTS);
            }
            if(email.equals(cust.getEmail())&&password.equals(cust.getPassword())) {
                return (ClientService) customerService;
            }else throw new CustomerSystemException(ErrMsg.NO_CUSTOMER_FOUND);
        }
        if (clientType.name().equals("COMPANY")) {
            CompanyService companyService = ctx.getBean(CompanyService.class);
            Company comp=companyService.getCompanyByEmail(email);
            if(comp==null){
                throw new CompanySystemException(ErrMsg.COMPANY_NOT_EXISTS);
            }
            if(email.equals(comp.getEmail())&&password.equals(comp.getPassword())) {
                return (ClientService) companyService;
            }else return null;
        }
        return null;
    }
}
