package com.project.shopapp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialAccount extends JpaRepository<com.project.shopapp.models.SocialAccount, Long> {
}
