package com.bhp.CouponSystem.repos;

import com.bhp.CouponSystem.beans.Category;
import com.bhp.CouponSystem.beans.Company;
import com.bhp.CouponSystem.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    Coupon findByCompanyIdAndTitle(int Id, String title);
    List<Coupon> findByCompanyIdAndCategory(int Id, Category category);
    List<Coupon> findByCompanyId(int Id);
    List<Coupon> findByCompanyIdAndPriceLessThan(int companyId,double price);
    Coupon findByTitle(String couponTitle);

    @Transactional
    @Modifying
    @Query(value="update coupons set amount=:amount where id=:id",nativeQuery = true)
    void updateCouponAmount(@Param("id")int id, @Param("amount") int amount);

    @Transactional
    @Modifying
    @Query(value="delete FROM coupunsystem.customers_coupons where coupons_id in(select id from coupunsystem.coupons where company_id= :companyId) " ,nativeQuery = true)
//            " where email= :customerEmail))" ,nativeQuery = true)
    void deletePurchasedCompanyCoupons(@Param("companyId") int companyId);
 @Transactional
    @Modifying
    @Query(value="delete FROM coupunsystem.customers_coupons where customer_id= :customerId " ,nativeQuery = true)
//            " where email= :customerEmail))" ,nativeQuery = true)
    void deletePurchasedCustomerCoupons(@Param("customerId") int customerId);

    @Transactional
    @Modifying
    @Query(value="delete FROM coupunsystem.coupons where id= :couponId and company_id= :companyId" ,nativeQuery = true)
//            " where email= :customerEmail))" ,nativeQuery = true)
    void deleteCompanyCoupons(@Param("couponId") int couponId,@Param("companyId") int companyId);
    @Transactional
    @Modifying
    @Query(value="delete FROM coupunsystem.coupons where company_id= :companyId" ,nativeQuery = true)
//            " where email= :customerEmail))" ,nativeQuery = true)
    void deleteAllCompanyCoupons(@Param("companyId") int companyId);

    @Transactional
    @Modifying
    @Query(value="delete FROM coupunsystem.coupons where end_date < :endDate" ,nativeQuery = true)
    void deleteExpiredCoupons(@Param("endDate") Date endDate);
}
