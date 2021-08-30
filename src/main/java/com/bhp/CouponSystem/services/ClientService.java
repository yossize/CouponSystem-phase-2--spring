package com.bhp.CouponSystem.services;
import com.bhp.CouponSystem.beans.ClientType;
import com.bhp.CouponSystem.exceptions.CompanySystemException;
import com.bhp.CouponSystem.exceptions.CustomerSystemException;
import com.bhp.CouponSystem.repos.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.repository.query.Param;

@NoArgsConstructor

public abstract class ClientService {
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CompanyRepository companyRepository;

    public abstract boolean login (String email,String password) throws Exception;

}
