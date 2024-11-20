package com.project.shopapp.Services;

import com.project.shopapp.dto.request.UserCreationRequest;
import com.project.shopapp.dto.response.UserResponse;
import com.project.shopapp.models.User;

public interface IUserService {
    UserResponse createUser(UserCreationRequest request);

//    String login(String phoneNumber, String password) throws Exception;
}
