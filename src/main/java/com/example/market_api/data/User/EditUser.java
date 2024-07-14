package com.example.market_api.data.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
