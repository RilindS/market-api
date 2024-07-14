package com.example.market_api.data.User;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserView {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;

    public UserView() {
        // Default constructor
    }
    public UserView(Long id, String firstName, String lastName, String email, String phoneNumber, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;


    }

}
