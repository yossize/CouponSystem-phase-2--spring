package com.bhp.CouponSystem.controller;

import com.bhp.CouponSystem.beans.Coupon;
import com.bhp.CouponSystem.beans.Company;
import com.bhp.CouponSystem.exceptions.CompanySystemException;
import com.bhp.CouponSystem.exceptions.CouponSystemException;
import com.bhp.CouponSystem.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public List<Company> getAllCompanys() throws CompanySystemException {
        return companyService.getAllCompanies();
    }

    @GetMapping("{/id}")
    //get Company details http://localhost:8080/cust/1
    public ResponseEntity<?> getCompanyById(@PathVariable int id ) throws CompanySystemException {
        //return CompanyService.getCompanyByEmail(email);
        try {
            return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{/email}")
    //get Company details http://localhost:8080/cust/1
    public ResponseEntity<?> getCompanyByEmail(@PathVariable String  email ) throws CompanySystemException {
        //return CompanyService.getCompanyByEmail(email);
        try {
            return new ResponseEntity<>(companyService.getCompanyByEmail(email), HttpStatus.OK);
        }
        catch(CompanySystemException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        }
        @PostMapping
        public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon,int id) throws CompanySystemException {
        try {
            companyService.addCoupon(coupon, id);
            return new ResponseEntity( HttpStatus.CREATED);
        }
        catch(CompanySystemException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        @PutMapping("{/id}")
        public ResponseEntity<?> updateCoupon(@PathVariable int id,@RequestBody Coupon coupon){
        try {
            companyService.updateCoupon(id,coupon);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        }

//        @DeleteMapping({"/id"})
//        public ResponseEntity<?> deleteCoupon(@PathVariable String couponTitle,@PathVariable String email){
//            try{
//                companyService.deleteCoupon( couponTitle, email);
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            catch(CompanySystemException e){
//                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
//            }
//        }
    }

