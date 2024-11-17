package com.project.shopapp.Repositories;

import com.project.shopapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Role, Long> {
}
