package com.project.shopapp.Repositories;

import com.project.shopapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Role, Long> {
}
