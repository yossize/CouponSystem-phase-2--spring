package com.bhp.CouponSystem.repos;

import com.bhp.CouponSystem.beans.Company;
import com.bhp.CouponSystem.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {

    boolean existsByEmail(String email);
    boolean existsByCompanyName(String name);
    Company findByEmail(String email);


//    @Query(value="select * from ...",nativeQuery = true)
//   List<Company> orenCorol();



}
