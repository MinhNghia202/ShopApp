package com.project.shopapp.Repositories;

import com.project.shopapp.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
