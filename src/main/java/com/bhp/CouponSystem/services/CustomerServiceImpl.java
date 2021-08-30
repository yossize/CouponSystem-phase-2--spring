package com.bhp.CouponSystem.services;

import com.bhp.CouponSystem.beans.Category;
import com.bhp.CouponSystem.beans.ClientType;
import com.bhp.CouponSystem.beans.Coupon;
import com.bhp.CouponSystem.beans.Customer;
import com.bhp.CouponSystem.exceptions.CompanySystemException;
import com.bhp.CouponSystem.exceptions.CouponSystemException;
import com.bhp.CouponSystem.exceptions.CustomerSystemException;
import com.bhp.CouponSystem.exceptions.ErrMsg;
import com.bhp.CouponSystem.repos.CouponRepository;
import com.bhp.CouponSystem.repos.CustomerRepository;
import com.bhp.CouponSystem.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CustomerServiceImpl extends ClientService implements CustomerService {
    @Autowired
    private LoginManager loginManger;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public boolean login(String email, String password) throws Exception {

        CustomerService customerService = (CustomerService) loginManger.login(email, password, ClientType.CUSTOMER);
        Customer cust = customerService.getCustomerByEmail(email);
        if (cust == null) {
            throw new CustomerSystemException(ErrMsg.NO_CUSTOMER_FOUND);
        }
        if ((!(cust.getEmail().equals(email)))) {
            throw new CustomerSystemException(ErrMsg.CUSTOMER_EMAIL_NOT_EXISTS);
        }
        if (!(cust.getPassword().equals(password))) {
            throw new CustomerSystemException(ErrMsg.CUSTOMER_WRONG_PASSWORD);
        }
        return true;
    }

    @Override
    public void addCoupon(Coupon coupon, String customerEmail) throws Exception {
        System.out.println("adding coupon in customerService");
        Customer cust = customerRepository.findByEmail(customerEmail);
        List<Coupon> customerCoupons = cust.getCoupons();
        if (customerCoupons.size() >0) {
            for (Coupon c : customerCoupons) {
                //can not purchase same coupon
                if (c.getTitle().equals(coupon.getTitle())) {
                    throw new CustomerSystemException(ErrMsg.CUSTOMER_COUPON_ALREADY_EXISTS);
                }
            }
        }
        //check there the coupon amount is GT zero
        if (couponRepository.findByTitle(coupon.getTitle()).getAmount() == 0) {
            throw new CustomerSystemException(ErrMsg.CUSTOMER_PURCHASE_COUPON_ZERO_AMOUNT);
        }
        //check if coupon is effective
        if(couponRepository.findById(coupon.getId()).orElseThrow(ExceptionUtils::couponNotFound).getEndDate()!=null) {
            if (couponRepository.findById(coupon.getId()).orElseThrow(ExceptionUtils::couponNotFound).getEndDate().before(Date.valueOf(LocalDate.now()))) {
                throw new CustomerSystemException(ErrMsg.CUSTOMER_PURCHASE_COUPON_IS_EXPIRED);
            }
        }
       //purchase the coupon
        customerRepository.saveAndFlush(cust);
        customerRepository.addCustomerCoupon(cust.getId(),coupon.getId());
        //decreament 1 from coupons
        couponRepository.updateCouponAmount(coupon.getId(), coupon.getAmount() - 1);
    }


    @Override
    public void deleteCoupon(String couponTitle, String customerEmail) throws CustomerSystemException {
        Customer cust = customerRepository.findByEmail(customerEmail);
        if (cust == null) {
            throw new CustomerSystemException(ErrMsg.NO_CUSTOMER_FOUND);
        }
        for (Coupon c : cust.getCoupons()) {
            if (c.getTitle() == couponTitle) {
                cust.getCoupons().remove(c);
            }
        }
        customerRepository.saveAndFlush(cust);

    }

    @Override
    public List<Coupon> getAllCouponsByEmail(String customerEmail) throws CustomerSystemException {
        Customer cust = customerRepository.findByEmail(customerEmail);
        if (cust == null) {
            throw new CustomerSystemException(ErrMsg.CUSTOMER_HAS_NO_COUPONS);
        }
        //todo - hoe to return list of coupons from customer
        return cust.getCoupons();
    }

    @Override
    public void updateCoupon(Coupon coupon, String customerEmail) throws CustomerSystemException {
        Customer cust = customerRepository.findByEmail(customerEmail);
        if (cust == null) {
            throw new CustomerSystemException(ErrMsg.NO_CUSTOMER_FOUND);
        }
        for (Coupon c : cust.getCoupons()) {
            if (c.getTitle() == coupon.getTitle()) {
                c.setAmount(coupon.getAmount());
                c.setCategory(coupon.getCategory());
                c.setDescription(coupon.getDescription());
                c.setImage(coupon.getImage());
            }
        }
        customerRepository.saveAndFlush(cust);
    }



    //  @Override
    //todo - how to get customer coupons by category
    //public List<Coupon> getAllCouponsByCustomerAndCategory(String customerEmail, Category category) throws CustomerSystemException {
//        List<Coupon> customerCoupons=customerRepository.findByCustomerIdAndCategory(customerId,category);
//        if(customerCoupons==null){
//            throw new CustomerSystemException(ErrMsg.CUSTOMER_HAS_NO_COUPONS_FOR_THIS_CATEGORY);
//        }
//        return customerCoupons;
//        return null;
//    }

    @Override
    public List<Coupon> getAllCouponsByMaxPrice(String customerEmail, float price) throws CustomerSystemException {
        return null;
    }

    @Override
    public Customer getCustomerByEmail(String customerEmail) throws CustomerSystemException {
        return customerRepository.findByEmail(customerEmail);
    }

    @Override
    public Customer getCustomerById(int id) throws CustomerSystemException {
        //todo - findbyid requires optional
//        Customer cust= customerRepository.findById(id);
//        return cust;
        Customer c = null;
        return c;
    }

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }


}
