package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public class BaseEntity {
    @Column(name = "created_at")
    LocalDate createdAt;
    @Column(name = "updated_at")
    LocalDate updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDate.now();
    }
}
