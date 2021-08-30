package com.bhp.CouponSystem.clr;

import com.bhp.CouponSystem.beans.ClientType;
import com.bhp.CouponSystem.beans.Company;
import com.bhp.CouponSystem.beans.Customer;
import com.bhp.CouponSystem.services.*;
import com.bhp.CouponSystem.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(3)
@RequiredArgsConstructor
public class UseCaseAdmin implements CommandLineRunner {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=====>LOGIN AS ADMIN");
        System.out.println("====================");
        System.out.println(Art.ADMIN_USE_CASE);
        System.out.println("try to Login as administrator adminGGG@admin.com/adminGGG");
        try {
            ClientService clientService = loginManager.login("adminGGG@admin.com", "adminGGG", ClientType.ADMINISTRATOR);
            System.out.println("no AdminService returned - no login for administrator");
        } catch (Exception e) {
            System.out.println("**EXCEPTION**" + e.getMessage());
        }
        System.out.println("try to Login as administrator admin@admin.com/admin");
        try {
            ClientService clientService = loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
            System.out.println("administrator logged in");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("=====>ADD COMPANY AS ADMIN");
        System.out.println("==========================");
        try {
            System.out.println("adding company Intel/intel@gmail.com/1234");
            adminService.addCompany(Company.builder()
                    .companyName("Intel")
                    .email("intel@gmail.com")
                    .password("1234")
                    .build());
        } catch (Exception e) {
            System.out.println("**Exception** " + e.getMessage());
        }
        System.out.println("try to add company with name that already exists");
        try {
            adminService.addCompany(Company.builder()
                    .companyName("Intel")
                    .email("intel123@gmail.com")
                    .password("1234")
                    .build());
        } catch (Exception e) {
            System.out.println("**Exception** " + e.getMessage());
        }
        System.out.println("try to add company with email that already exists");
        try {
            adminService.addCompany(Company.builder()
                    .companyName("Nvidia")
                    .email("intel@gmail.com")
                    .password("1234")
                    .build());
        } catch (Exception e) {
            System.out.println("**Exception** " + e.getMessage());
        }
        System.out.println("=====>UPDATE COMPANY AS ADMIN");
        System.out.println("=============================");
        List<Company> companies = adminService.getAllCompanies();
        if (companies != null) {
            Company comp = companies.get(0);
            System.out.println("working on company " + comp);
            try {
                System.out.println("trying to update company's password to 1212");
                comp.setPassword("1212");
                adminService.updateCompany(comp);
            } catch (Exception e) {
                System.out.println("**EXCEPTION " + e.getMessage());
            }
            System.out.println("trying to update company's name");
            comp.setCompanyName("ccc");
            try {
                adminService.updateCompany(comp);
            } catch (Exception e) {
                System.out.println("**EXCEPTION** " + e.getMessage());
            }

            System.out.println("trying to update company's id");
            int companyIdSave = comp.getId();
            comp.setId(comp.getId() + 44);
            try {
                adminService.updateCompany(comp);
            } catch (Exception e) {
                System.out.println("**EXCEPTION " + e.getMessage());
            }
            System.out.println("=====>DELETE COMPANY AS ADMIN");
            System.out.println("=============================");
            try {

                System.out.println("delete company id " + companyIdSave);
                adminService.deleteCompany(companyIdSave);
                System.out.println("delete company id 44 that doesnt exists");
                adminService.deleteCompany(companyIdSave + 44);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("no company found for update or delete");
        }
        System.out.println("=====>GET ALL COMPANIES AS ADMIN");
        System.out.println("================================");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("=====>GET A SINGLE COMPANY AS ADMIN");
        System.out.println("===================================");
        System.out.println(adminService.getSingleCompany(2));

        System.out.println("=====>ADD CUSTOMER AS ADMIN");
        System.out.println("===========================");
        System.out.println("add new customer");
        adminService.addCustomer(Customer.builder()
                .firstName("Haim")
                .lastName("Moshe")
                .email("Haim.Moshe@gmail.com")
                .password("1234")
                .build());
        System.out.println("add existing customer");
        Customer cust = adminService.getSingleCustomer(1);
        try {

            adminService.addCustomer(Customer.builder()
                    .email(cust.getEmail())
                    .password("1222")
                    .firstName("Haim")
                    .lastName("Haim")
                    .build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("=====>UPDATE CUSTOMER AS ADMIN");
        System.out.println("==============================");
        try {
            System.out.println("updating customer password");
            cust.setPassword("223344");
            adminService.updateCustomer( cust);
            System.out.println("trying to update customer id");
            cust.setId(11);
            adminService.updateCustomer(cust);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("=====>DELETE CUSTOMER AS ADMIN");
        System.out.println("==============================");
        adminService.deleteCustomer(1);

        System.out.println("=====>GET ALL CUSTOMERS AS ADMIN");
        System.out.println("================================");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("=====>GET SINGLE CUSTOMER AS ADMIN");
        System.out.println("==================================");
        System.out.println(adminService.getSingleCustomer(3));

    }

}
