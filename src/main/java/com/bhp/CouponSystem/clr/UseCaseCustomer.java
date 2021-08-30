package com.bhp.CouponSystem.clr;

import com.bhp.CouponSystem.beans.*;
import com.bhp.CouponSystem.exceptions.CouponSystemException;
import com.bhp.CouponSystem.exceptions.ErrMsg;
import com.bhp.CouponSystem.repos.CompanyRepository;
import com.bhp.CouponSystem.repos.CouponRepository;
import com.bhp.CouponSystem.repos.CustomerRepository;
import com.bhp.CouponSystem.services.*;
import com.bhp.CouponSystem.utils.Art;
import com.bhp.CouponSystem.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(3)
@RequiredArgsConstructor
public class UseCaseCustomer implements CommandLineRunner {
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    LoginManager loginManager;
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CustomerRepository customerRepository;
    private final String customerEmail="haim.cohen@gmail.com";
    @Override
    public void run(String... args) throws Exception {
        System.out.println("=====>LOGIN AS CUSTOMER");
        System.out.println("====================");
        System.out.println(Art.CUSTOMER_USE_CASE);

        System.out.println("login as customer. login details stamcustomer@gmail.com/1234");
        try{
            ClientService clientService=loginManager.login("stamcustomer@gmail.com","1234", ClientType.CUSTOMER);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("login as customer. login details " +customerEmail+"/1234");
        try {
            ClientService clientService = loginManager.login(customerEmail, "1234", ClientType.CUSTOMER);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("=====>PURCHASE COUPON AS CUSTOMER");
        System.out.println("==========================");

        try {
            //Coupon c1 = couponRepository.findById(1).orElseThrow(() -> new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS));
            Coupon c1 = couponRepository.findById(1).orElseThrow(ExceptionUtils::couponNotFound);

            customerService.addCoupon(c1, "Yossi.cohen@gmail.com");
        }
        catch(Exception e){
            System.out.println("**Exception** "+e.getMessage());
        }
        System.out.println("=====>GET ALL PURCHASED COUPONS BY customer");
        System.out.println("=============================");
        customerService.getAllCouponsByEmail(customerEmail).forEach(System.out::println);
        System.out.println("=====>GET ALL PURCHASED COUPONS BY CUSTOMER AND CATEGORY");
        System.out.println("=============================");
       // customerRepository.getAllCustomerCouponsByCategory(Category.FOOD.ordinal(),customerEmail).forEach(System.out::println);
       // customerRepository.getAllCustomerCouponsByCategory(Category.FOOD.ordinal()).forEach(System.out::println);
        //customerRepository.getAllCustomerCouponsByCategory().forEach(System.out::println);
        customerRepository.getAllCustomerCouponsByCategory().forEach(System.out::println);

        System.out.println("=====>GET customer DETAILS");
        System.out.println("===========================");
        System.out.println(customerService.getCustomerByEmail(customerEmail));

    }
}
