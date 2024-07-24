package com.example.market_api.data.Supplier;

import com.example.market_api.data.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupplier extends Base {
    private String name;
    private String contactInfo;
    private String address;
    private String phone;
    private String email;
}
