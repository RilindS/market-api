package com.example.market_api.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categorys;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier suppliers;

}
