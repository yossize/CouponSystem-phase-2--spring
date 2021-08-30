package com.bhp.CouponSystem.clr;

import com.bhp.CouponSystem.beans.ClientType;
import com.bhp.CouponSystem.beans.Company;
import com.bhp.CouponSystem.beans.Customer;
import com.bhp.CouponSystem.exceptions.CompanySystemException;
import com.bhp.CouponSystem.exceptions.CustomerSystemException;
import com.bhp.CouponSystem.services.*;
import com.bhp.CouponSystem.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class UseCaseLogin implements CommandLineRunner {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private LoginManager loginManager;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(Art.LOGIN);
        System.out.println("login as administrator with admin@admin.com/admin");
        try {
            ClientService clientService = loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
            if (clientService instanceof AdminService) {
                System.out.println("no AdminService returned - no login for administrator");
            } else {
                System.out.println("administrator logged in");
            }
        }
        catch(Exception e){
            System.out.println("**Exception** "+e.getMessage());
        }
        System.out.println("login as administrator with adminaa@admin.com/admin");
        try{
        ClientService clientService=loginManager.login("adminaa@admin.com","admin", ClientType.ADMINISTRATOR);
        if (clientService instanceof AdminService){
            System.out.println("returned AdminService - administrator logged in");
        }else{
            System.out.println("administrator not logged in");
        }
        }
        catch(Exception e){
            System.out.println("**Exception** "+ e.getMessage());
        }

        System.out.println("login as customer with avi.cohen@gmail.com/1234");
        try {
            ClientService clientService = loginManager.login("avi.cohen@gmail.com", "1234", ClientType.CUSTOMER);
            if (clientService instanceof CustomerService) {
                System.out.println("avi.cohen@gmail.com logged in");
            } else {
                System.out.println(" no login for avi.cohen@gmail.com ");
            }
        }
            catch (Exception e){
                System.out.println("no login with avi@gmail, 1234");
            }
        System.out.println("login as customer with avi@gmail.com/1234");
        try{
            ClientService clientService=loginManager.login("avi@gmail","1234", ClientType.CUSTOMER);
        if (clientService instanceof CustomerService){
            System.out.println("avi@gmail logged in");
        }else{
            System.out.println(" no login for avi@gmail");
        }
        }
        catch (Exception e){
            System.out.println("customer avi@gmail not logged in");
        }
        System.out.println("login as company with BBB@gmail.com/1234");
        try {
            ClientService clientService = loginManager.login("BBB@gmail.com", "1234", ClientType.COMPANY);
            if (clientService instanceof CompanyService) {
                System.out.println("BBB@gmail.com logged in");
            } else {
                System.out.println(" no login for BBB@gmail");
            }
            System.out.println("login as company with BBB@gmail.com/1234");
        }
        catch(Exception e){
            System.out.println("company ddd@gmail.com/1234 not logged in");
        }
        try {
            ClientService clientService = loginManager.login("ddd@gmail", "1234", ClientType.CUSTOMER);
            if (clientService instanceof CompanyService) {
                System.out.println("ddd@gmail logged in");
            } else {
                System.out.println(" no login for ddd@gmail");
            }
        }
            catch(Exception e){
                System.out.println("company ddd@gmail.com/1234 not logged in");
            }
        }
    }
