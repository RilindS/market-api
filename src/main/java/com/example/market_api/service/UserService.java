package com.example.market_api.service;


import com.example.market_api.common.ResponseObject;
import com.example.market_api.data.User.DeleteUser;
import com.example.market_api.data.User.UserView;
import com.example.market_api.entity.User;
import com.example.market_api.exception.InternalException;
import com.example.market_api.repository.NativeQueryRepository;
import com.example.market_api.repository.RoleRepository;
import com.example.market_api.repository.UserRepository;
import com.example.market_api.security.auth.AuthenticationResponse;
import com.example.market_api.security.auth.RegisterRequest;
import com.example.market_api.security.service.JwtService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserService extends BaseService{

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final NativeQueryRepository nativeQueryRepository;

    public UserService(PasswordEncoder passwordEncoder,RoleRepository roleRepository,UserRepository userRepository, JwtService jwtService,NativeQueryRepository nativeQueryRepository  ) {
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
        this.nativeQueryRepository=nativeQueryRepository;
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
    public ResponseObject getAllUsers(Integer page, Integer size, String search) {
        String methodName = "Get all Users";
        log.info("getAllUsers: {} - page: {}, size: {}, search: {}", methodName, page, size, search);

        ResponseObject responseObject = new ResponseObject();
        try {
            List<UserView> userList = nativeQueryRepository.GetAllUsers(page, size);
            log.info("Fetched {} users", userList.size());

            Long count = nativeQueryRepository.getUsersCount(search);
            Pageable sortedById = PageRequest.of(page - 1, size);

            Page<UserView> resultPage = new PageImpl<>(userList, sortedById, count);

            responseObject.setData(mapPage(resultPage, userList));
            responseObject.prepareHttpStatus(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error in {}: {}", methodName, e.getMessage(), e);
            throw new InternalException("Internal Error");
        }
        log.info("getAllUsers: {} - Completed successfully", methodName);
        return responseObject;
    }

    public ResponseObject deleteUser(Long userId) {
        String methodName="delete user by Id";
        log.info("deleteUser: {} - {}", methodName, userId);

        ResponseObject responseObject=new ResponseObject<>();
        try {

            Optional<User> user = userRepository.findById(userId);

            if(user.isPresent()){
                userRepository.deleteById(userId);
                responseObject.prepareHttpStatus(HttpStatus.OK);
                responseObject.setData("userd deleted successfuly"+user.get());
            }else{
                responseObject.setData("not found user with this id "+userId);
                responseObject.prepareHttpStatus(HttpStatus.NOT_FOUND);

            }
        }catch (Exception e){
            log.error("Error in {}: {}", methodName, e.getMessage(), e);
            throw new InternalException("internal error");

        }
        log.info("deleteUser: {} - Completed successfully", methodName);
        return responseObject;
    }


}
