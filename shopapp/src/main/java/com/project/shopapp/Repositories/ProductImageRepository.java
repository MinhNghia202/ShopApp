package com.project.shopapp.Repositories;

import com.project.shopapp.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {


    List<ProductImage> findAllByProductId(Long productId);
}
