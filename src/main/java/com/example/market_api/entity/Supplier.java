package com.example.market_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {
    private String name;
    private String contactInfo;
    private String address;
    private String phone;
    private String email;


    @ManyToMany(mappedBy = "suppliers")

    private List<Product> products;




}
