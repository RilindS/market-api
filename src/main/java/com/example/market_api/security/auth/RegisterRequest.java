package com.example.market_api.security.auth;

import com.example.market_api.data.Base;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest extends Base {

    private String firstName;
    private String lastName;

    @NotBlank(message = "must.not.be.empty")
    @Email(message = "email.is.not.valid")
    private String email;

    @Size(min = 5, max =45, message = "password.min.max")
    private String password;

    private List<Long> categoryTicket;
    private List<Long> subCategoryTicket;
    private String phoneNumber;
    private String imageUrl;
    private String role;
}