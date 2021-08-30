package com.bhp.CouponSystem.job;

import com.bhp.CouponSystem.repos.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Component
@Order(4)
@RequiredArgsConstructor
public class DailyRemoval {
    private final CouponRepository couponRepository;

    @Scheduled(fixedRate = 1000*60*60*24)
    public void deleteExpiredCoupons(Date endDate){


    }

}
