package com.bhp.CouponSystem.clr;

import com.bhp.CouponSystem.beans.*;
import com.bhp.CouponSystem.exceptions.CompanySystemException;
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
import java.util.Arrays;

@Component
@Order(1)
@RequiredArgsConstructor
public class BootsStrap implements CommandLineRunner {
    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(Art.BOOTSTRAP_1);

        System.out.println(Art.COMPANIES);
        Company c1 = Company.builder()
                .email("Yamit2000@gmail.com")
                .companyName("Yamit2000")
                .password("1234")
                .build();
        Company c2 = Company.builder()
                .email("BBB@gmail.com")
                .companyName("BBB")
                .password("1234")
                .build();
        Company c3 = Company.builder()
                .email("Ivory@gmail.com")
                .companyName("Ivory")
                .password("1234")
                .build();
        companyRepository.saveAll(Arrays.asList(c1, c2, c3));
        companyRepository.findAll().forEach(System.out::println);
        //***check that can not add existing company
        //adminService.addCompany(c3);
        //***check that can not update company's name or id
        //c1.setCompanyName("AAA");
        //adminService.updateCompany(5,c1);

        System.out.println(Art.COUPONS);
        Coupon cop1 = Coupon.builder()
                .title("50% off")
                .description("half price")
                .amount(50)
                .price(3.5)
                .image("http://www.google.com")
                .category(Category.FOOD)
                .startDate(Date.valueOf(LocalDate.now().plusDays(12)))
                .endDate(Date.valueOf(LocalDate.now().plusDays(102)))
                .company(companyRepository.findById(1).orElseThrow(ExceptionUtils::companyNotFound))
                .build();
        Coupon cop2 = Coupon.builder()
                .title("25% off")
                .description("quarter price")
                .amount(40)
                .company(companyRepository.findById(1).orElseThrow(ExceptionUtils::companyNotFound))
                .price(6.5)
                .image("http://www.google.com")
                .category(Category.FOOD)
                .startDate(Date.valueOf(LocalDate.now().plusDays(12)))
                .endDate(Date.valueOf(LocalDate.now().plusDays(102)))
                .build();

        couponRepository.saveAll(Arrays.asList(cop1, cop2));
        couponRepository.findAll().forEach(System.out::println);

        System.out.println(Art.CUSTOMERS);
        Customer cust1 = Customer.builder()
                .firstName("Avi")
                .lastName("Cohen")
                .email("avi.cohen@gmail.com")
                .password("1234")
                .coupon(cop1)
                .build();
        Customer cust2 = Customer.builder()
                .firstName("Haim")
                .lastName("Cohen")
                .email("Haim.cohen@gmail.com")
                .password("1234")
                .coupon(cop2)
                .build();
        Customer cust3 = Customer.builder()
                .firstName("Yossi")
                .lastName("Cohen")
                .email("Yossi.cohen@gmail.com")
                .password("1234")
                .build();

        customerRepository.saveAll(Arrays.asList(cust1, cust2, cust3));
        customerRepository.findAll().forEach(System.out::println);
        //  customerRepository.getAllCouponsByEmail("avi.cohen@gmail.com");


    }
}
