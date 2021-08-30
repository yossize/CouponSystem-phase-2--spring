package com.bhp.CouponSystem.controller;

import com.bhp.CouponSystem.beans.Coupon;
import com.bhp.CouponSystem.beans.Customer;
import com.bhp.CouponSystem.exceptions.CouponSystemException;
import com.bhp.CouponSystem.exceptions.CustomerSystemException;
import com.bhp.CouponSystem.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cust")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() throws CustomerSystemException {
        return customerService.getAllCustomers();
    }
//    public List<Customer> getAllCustomers() throws CustomerSystemException {
//        return customerService.getAllCustomers();
//    }

    @GetMapping("{/email}")
    //get customer details http://localhost:8080/cust/1
    public ResponseEntity<?> getCustomerByEmail(@PathVariable String  email ) throws CustomerSystemException {
        //return customerService.getCustomerByEmail(email);
        try {
            return new ResponseEntity<>(customerService.getCustomerByEmail(email), HttpStatus.OK);
        }
        catch(CustomerSystemException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        }
        @PostMapping
        public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon,String email) throws Exception {
        try {
            customerService.addCoupon(coupon, email);
            return new ResponseEntity( HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
        @PutMapping("{/id}")
        public ResponseEntity<?> updateCoupon(@PathVariable String email,@RequestBody Coupon coupon){
        try {
            customerService.updateCoupon(coupon,email);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(CustomerSystemException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        }

        @DeleteMapping({"/id"})
        public ResponseEntity<?> deleteCoupon(@PathVariable String couponTitle,@PathVariable String email){
            try{
                customerService.deleteCoupon( couponTitle, email);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            catch(CustomerSystemException e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }
    }

