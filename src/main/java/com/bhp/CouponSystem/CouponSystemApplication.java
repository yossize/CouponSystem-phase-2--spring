package com.bhp.CouponSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class CouponSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(CouponSystemApplication.class, args);
		System.out.println("coupon system is running...");
	}

}
