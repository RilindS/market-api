package com.example.market_api.data.User;


import com.example.market_api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    public UserView(Long id, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;



    }

    public UserView(Long id, LocalDateTime createdAt, String createdBy, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }


    public static UserView fromEntity(User user) {

        return new UserView(
                user.getId(),
                user.getCreatedAt(),
                user.getCreatedBy(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),


                user.getPhoneNumber()

        );
    }
}
