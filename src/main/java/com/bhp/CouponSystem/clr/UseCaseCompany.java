package com.bhp.CouponSystem.clr;

import com.bhp.CouponSystem.beans.*;
import com.bhp.CouponSystem.services.*;
import com.bhp.CouponSystem.utils.Art;
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
public class UseCaseCompany implements CommandLineRunner {
    @Autowired
    private CompanyServiceImpl companyService;
    @Autowired
    LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=====>LOGIN AS COMPANY");
        System.out.println("====================");
        System.out.println(Art.COMPANY_USE_CASE);
        System.out.println("login as company. login details stamcompany@gmail.com/1234");
        try {
            ClientService clientService = loginManager.login("stamcompany@gmail.com", "1234", ClientType.COMPANY);
            if (clientService instanceof CompanyService) {
                System.out.println("no companyService returned - no login for companyService");
            } else {
                System.out.println("company logged in");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("login as company. login details ivory@gmail.com/1234");
        ClientService clientService = loginManager.login("ivory@gmail.com", "1234", ClientType.COMPANY);
        if (clientService instanceof CompanyService) {
            System.out.println("no companyService returned - no login for companyService");
        } else {
            System.out.println("companyService logged in");
        }
        System.out.println("=====>ADD COUPON AS COMPANY");
        System.out.println("==========================");
        Coupon c1 = Coupon.builder()
                .startDate(Date.valueOf(LocalDate.now().plusDays(15)))
                .endDate(Date.valueOf(LocalDate.now().plusDays(105)))
                .amount(45)
                .price(45.6)
                .category(Category.FOOD)
                .title("Buy lunch get free drink")
                .description("Buy lunch get free drink")
                .image("image1")
                .company(companyService.getCompanyById(1))
                .build();
        System.out.println("adding company " + c1);
        try {
            companyService.addCoupon(c1, 1);
        }
        catch(Exception e){
            System.out.println("**Exception** "+e.getMessage());
        }
        System.out.println("adding another coupon to company 1");
        try {
            companyService.addCoupon(Coupon.builder()
                    .startDate(Date.valueOf(LocalDate.now().plusDays(15)))
                    .endDate(Date.valueOf(LocalDate.now().plusDays(105)))
                    .amount(57)
                    .price(45.6)
                    .category(Category.FOOD)
                    .company(companyService.getCompanyById(1))
                    .title("Buy lunch get free salads")
                    .description("Buy lunch get free salads")
                    .image("image2")
                    .category(Category.FOOD)
                    .build(), 1);
        }
        catch(Exception e){
            System.out.println("**Exception** "+e.getMessage());
        }
        System.out.println("trying to add coupon with title already exists");
        try {
            companyService.addCoupon(Coupon.builder()
                    .startDate(Date.valueOf(LocalDate.now().plusDays(15)))
                    .endDate(Date.valueOf(LocalDate.now().plusDays(105)))
                    .amount(57)
                    .price(45.6)
                    .category(Category.FOOD)
                    .company(companyService.getCompanyById(1))
                    .title("Buy lunch get free salads")
                    .description("Buy lunch get free salads")
                    .image("image2")
                    .category(Category.FOOD)
                    .build(), 1);
        }
        catch(Exception e){
            System.out.println("**Exception** "+e.getMessage());
        }
        System.out.println("=====>UPDATE COUPON AS COMPANY");
        System.out.println("=============================");
        List<Company>  companies = companyService.getAllCompanies();
        if (companies.size()==0){
            System.out.println("no companies exists");
        }else{
            int companyId=companies.get(0).getId();
            List<Coupon> coupons=companyService.getAllCouponsByCompanyId(companyId);
            if (coupons != null) {
                coupons.forEach(System.out::println);
                int coupondIdSave = coupons.get(1).getId();
                coupons.get(1).setId(7);
                System.out.println("try to update coupon's id");
                try {
                    companyService.updateCoupon(coupondIdSave, coupons.get(1));
                } catch (Exception e) {
                    System.out.println("**Exception** "+e.getMessage());
                }
                coupons.get(0).setId(coupondIdSave);
                System.out.println("try to update coupon's company id");
                try {
                    Coupon cp=coupons.get(1);
                    Company cm=cp.getCompany();
                    cm.setId(54);
                    cp.setCompany(cm);
                    companyService.updateCoupon(cp.getId(), cp);
                } catch (Exception e) {
                    System.out.println("**Exception** "+e.getMessage());
                }
                coupons.get(0).setId(coupondIdSave);

            } else System.out.println("no coupons exists to comapny 1");

        }
        System.out.println("=====>DELETE COUPON AS COMPANY");
        System.out.println("=============================");
        try {
            Company compDelete = companyService.getCompanyById(1);
            if (compDelete != null) {
                List<Coupon> coupons = compDelete.getCoupons();
                if (coupons.size() >0) {
                    int coupondIdSave = coupons.get(1).getId();
                    int companyIdSave=coupons.get(1).getCompany().getId();
                    System.out.println("delete coupon id " + coupondIdSave);
                    try {
                        companyService.deleteCoupon(coupondIdSave, companyIdSave);
                    }
                    catch(Exception e){
                        System.out.println("**Exception** "+e.getMessage());
                    }
                    System.out.println("delete coupon id 44 that doesnt exists");
                    try {
                        companyService.deleteCoupon(coupondIdSave,44);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        //todo - delete coupons purchase history
                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=====>GET ALL COMPANY'S 1 COUPONS");
        System.out.println("================================");
        companyService.getAllCouponsByCompanyId(1).forEach(System.out::println);
        System.out.println("=====>GET ALL COMPANY'S 1 CATEGORY FOOD COUPONS");
        System.out.println("===================================");
        System.out.println(companyService.getAllCouponsByCompanyAndCategory(1, Category.FOOD));
        System.out.println("=====>GET ALL COMPANY'S 1 BY MAX PRICE COUPONS");
        System.out.println("===================================");
        System.out.println(companyService.getAllCouponsByCompanyUpToPrice(1, 70));

        System.out.println("=====>GET COMPANY DETAILS");
        System.out.println("===========================");
        System.out.println(companyService.getCompanyById(1));

    }
}
