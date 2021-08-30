package com.bhp.CouponSystem.repos;

import com.bhp.CouponSystem.beans.Category;
import com.bhp.CouponSystem.beans.Coupon;
import com.bhp.CouponSystem.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByEmail(String email);
    Customer findByEmail(String email);
    List<Coupon> getAllCouponsByEmail(String customerEmail) ;
    List<Coupon> findCouponsByEmail(String email);
  //  List<Coupon> findByEmailAndCategory(String email, Category category);

    @Transactional
    @Modifying
    @Query(value="update coupons set amount=:amount where id=:id",nativeQuery = true)
    void updateCouponAmount(@Param("id")int id, @Param("amount") int amount);

    @Transactional
    @Modifying
    @Query(value="insert into customers_coupons values(:custId,:couponId)" ,nativeQuery = true)
    void addCustomerCoupon(@Param("custId") int custId,@Param("couponId") int couponId);

    @Transactional
    //@Modifying
    @Query(value="SELECT * FROM coupunsystem.coupons " ,nativeQuery = true)
 //   @Query(value="SELECT * FROM coupunsystem.coupons where category= :category " ,nativeQuery = true)
//    @Query(value="SELECT * FROM coupunsystem.coupons where category= :category and id in\n" +
//            "(select coupons_id from coupunsystem.customers_coupons where "+
//            " customer_id in"+
//            "(select id from coupunsystem.customers "+
//            " where email= :customerEmail))" ,nativeQuery = true)
 //   @Query(value="SELECT * FROM coupunsystem.coupons where category= :category  in (select coupons_id from coupunsystem.customers_coupons where customer_id in (select id from coupunsystem.customers where email= :customerEmail))" ,nativeQuery = true)
   // List<Coupon> getAllCustomerCouponsByCategory();
    List<Object> getAllCustomerCouponsByCategory();
    //List<Coupon> getAllCustomerCouponsByCategory(@Param("category") int category);
   // List<Coupon> getAllCustomerCouponsByCategory(@Param("category")int category,@Param("customerEmail") String email);
}
