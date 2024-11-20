package com.project.shopapp.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "token_out")
public class InvalidatedToken {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    String id;
    @Column(name = "expiry_time")
    Date expiryTime;
}
