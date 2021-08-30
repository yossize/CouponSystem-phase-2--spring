package com.bhp.CouponSystem.beans;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String companyName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    @ToString.Exclude
    @Singular
    private List<Coupon> coupons=new ArrayList<>();

}
