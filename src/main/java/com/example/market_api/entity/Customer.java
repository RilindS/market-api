package com.example.market_api.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer extends BaseEntity{

    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
