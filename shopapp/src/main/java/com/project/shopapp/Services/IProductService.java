package com.project.shopapp.Services;

import com.project.shopapp.dto.request.ProductCreationRequest;
import com.project.shopapp.dto.request.ProductImageCreationRequest;
import com.project.shopapp.dto.response.ProductResponse;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IProductService {
    public ProductResponse createProduct(ProductCreationRequest request);
    public ProductResponse getProductById(long id);
    Page<ProductResponse> getAllProduct(PageRequest request);
    public ProductResponse updateProduct(long id, ProductCreationRequest request);
    public void deleteProduct(long id);
    boolean existsByName(String name);
    public ProductImage createProductImage(long productId, ProductImageCreationRequest request);

}
