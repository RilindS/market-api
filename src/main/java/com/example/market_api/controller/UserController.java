package com.example.market_api.controller;

import com.example.market_api.common.ResponseObject;
import com.example.market_api.data.User.DeleteUser;
import com.example.market_api.data.User.UserView;
import com.example.market_api.entity.User;
import com.example.market_api.security.auth.AuthenticationRequest;
import com.example.market_api.security.auth.AuthenticationResponse;
import com.example.market_api.security.auth.AuthenticationService;
import com.example.market_api.security.auth.RegisterRequest;
import com.example.market_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//import static jdk.internal.jrtfs.JrtFileAttributeView.AttrID.size;

@Log4j2
@RequestMapping("${base.url}/user")@Controller
@RestController

public class UserController {

    private UserService userService;
    private  AuthenticationService sevice;

    public UserController(UserService userService, AuthenticationService sevice) {
        this.userService = userService;
        this.sevice = sevice;
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse>register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(sevice.authentication(request));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })

    //@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<AuthenticationResponse> createUser(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "sucessful opertion"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),

    })
    @GetMapping("/allUsers")
    public ResponseEntity getAllUsers(@RequestParam ("page")Integer page,@RequestParam("size")Integer size,@RequestParam(required = false ,name ="serach")String search ) {

        String methodName = "getAllUsers";
        log.info("getAll ticket:"+methodName);
        ResponseObject  responseObject = userService.getAllUsers(page,size,search);

        log.info("{} -> Get all Users, response status: {}",methodName, responseObject.getCode());

        return ResponseEntity.ok(responseObject);
    }
    @Operation(summary = "Delete User", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Delete created",
                    content = @Content(schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Conflict") })
    @PostMapping("/delete")
    public ResponseEntity<ResponseObject> deleteUser(@RequestBody @Valid DeleteUser deleteUser) {
        String methodName = "delete user";
        log.info("deleteUser :" + methodName);
        ResponseObject responseObject = userService.deleteUser(deleteUser.getId());

        return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
    }

    @Operation(summary = "Edit User", description = "Edits the details of an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserView.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PutMapping("/edit/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserView> editUser(@RequestBody @Valid RegisterRequest request, @PathVariable Long userId
    ) {
        String methodName = "editUser";

        log.info("{} -> Edit User", methodName);

        UserView editedUser = userService.editUser(request, userId);
        log.info("{} -> Edit User, response status: 200", methodName);
        return ResponseEntity.status(HttpStatus.OK).body(editedUser);
    }



}
