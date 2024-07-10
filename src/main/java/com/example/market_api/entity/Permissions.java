package com.example.market_api.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "permissions")


public class Permissions extends BaseEntity {


    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "premissions",fetch = FetchType.LAZY)
    private List<RolePermissions> rolePermissions;

}
