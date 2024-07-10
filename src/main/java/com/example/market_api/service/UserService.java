package com.example.market_api.service;


import com.example.market_api.entity.User;
import com.example.market_api.repository.RoleRepository;
import com.example.market_api.repository.UserRepository;
import com.example.market_api.security.auth.AuthenticationResponse;
import com.example.market_api.security.auth.RegisterRequest;
import com.example.market_api.security.auth.service.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

@Log4j2
@Service
public class UserService extends BaseService{

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(PasswordEncoder passwordEncoder,RoleRepository roleRepository,UserRepository userRepository, JwtService jwtService  ) {
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
        this.jwtService=jwtService;
    }

    public AuthenticationResponse createUser(RegisterRequest request) {
        try {
            validateRegisterRequest(request);
            String logoUrl = null;



            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(roleRepository.findByName(request.getRole()))
                    .phoneNumber(request.getPhoneNumber())
                    .build();

            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder().token(jwtToken).userId(user.getId()).build();
        } catch (ValidationException e) {
            throw new ValidationException("Validation error");
        }

    }
    private void validateRegisterRequest(RegisterRequest request) {
        if (request.getFirstName() == null || request.getFirstName().isEmpty() ||
                request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new ValidationException("Please fill the required  fields");
        }
    }
}
