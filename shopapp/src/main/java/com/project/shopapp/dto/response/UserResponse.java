package com.project.shopapp.dto.response;

import com.project.shopapp.models.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    long id;
    String username;
    String password;
    String fullName;
    String phoneNumber;
    String address;
    LocalDate createdAt;
    LocalDate updatedAt;
    boolean isActive;
    Date dateOfBirth;
    int facebookAccountId;
    int googleAccountId;
    String roleName;
}
