package com.project.shopapp.controllers;

import com.project.shopapp.Services.UserService;
import com.project.shopapp.dto.request.ApiResponse;
import com.project.shopapp.dto.request.UserCreationRequest;
import com.project.shopapp.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }
//    @PostMapping("/login")
//    public ApiResponse<String> login(@RequestBody UserLoginRequest request){
//        try {
//            String token = userService.login(request.getPhoneNumber(), request.getPassword());
//            return ApiResponse.<String>builder()
//                    .result(token)
//                    .build();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
