package com.project.shopapp.Services;

import com.project.shopapp.Repositories.RoleRepository;
import com.project.shopapp.Repositories.UserRepository;
import com.project.shopapp.dto.request.UserCreationRequest;
import com.project.shopapp.dto.response.UserResponse;
import com.project.shopapp.exception.AppException;
import com.project.shopapp.exception.ErrorCode;
import com.project.shopapp.models.Role;
import com.project.shopapp.models.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService implements IUserService{
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserCreationRequest request) {
        String phoneNumber = request.getPhoneNumber();
        if(userRepository.existsByPhoneNumber(phoneNumber)){
            throw (new DataIntegrityViolationException("Phone number already exists"));
        }
        Role role = roleRepository.findByName("user")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .dateOfBirth(request.getDateOfBirth())
                .facebookAccountId(request.getFacebookAccountId())
                .googleAccountId(request.getGoogleAccountId())
                .role(role)
                .build();
//        if(roleRepository.findByRoleName("user").isEmpty()){
//            Role roleUser = Role.builder()
//                    .id(1)
//                    .name("user")
//                    .build();
//            roleRepository.save(roleUser);
//            user.setRole(roleUser);
//        }else {
//            long id = 1;
//            Role role = roleRepository.findById(id)
//                    .orElseThrow(()-> new AppException(ErrorCode.ROLE_NOT_FOUND));
//            user.setRole(role);
//        }


        //Kiểm tra nếu có account id thì không cần password
        if(request.getGoogleAccountId() == 0 && request.getFacebookAccountId() == 0){
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        userRepository.save(user);
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .isActive(user.isActive())
                .dateOfBirth(user.getDateOfBirth())
                .facebookAccountId(user.getFacebookAccountId())
                .googleAccountId(user.getGoogleAccountId())
                .roleName(user.getRole().getName())
                .build();
    }
}
