package com.project.shopapp.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String username;
    String password;
    String fullName;
    String phoneNumber;
    String address;
    Date dateOfBirth;
    int facebookAccountId;
    int googleAccountId;
}
