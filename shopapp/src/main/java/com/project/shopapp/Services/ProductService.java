package com.project.shopapp.Services;

import com.project.shopapp.Repositories.CategoryRepository;
import com.project.shopapp.Repositories.ProductImageRepository;
import com.project.shopapp.Repositories.ProductRepository;
import com.project.shopapp.dto.request.ProductCreationRequest;
import com.project.shopapp.dto.request.ProductImageCreationRequest;
import com.project.shopapp.dto.response.CategoryResponse;
import com.project.shopapp.dto.response.ProductResponse;
import com.project.shopapp.models.Category;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public ProductResponse createProduct(ProductCreationRequest request) {
        Category existigncategory = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category not found!"));
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .thumbnail(request.getThumbnail())
                .category(existigncategory)
                .build();
        productRepository.save(product);

        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(existigncategory.getId())
                .name(existigncategory.getName())
                .build();
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .thumbnail(product.getThumbnail())
                .category(categoryResponse)
                .build();
    }

    private Product getById(long id){
        Product product = productRepository.findById(id).orElseThrow();
        log.info("Product: " + product.getId());
        return product;
    }

    @Override
    public ProductResponse getProductById(long id) {
        Product product = getById(id);
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .thumbnail(product.getThumbnail())
                .category(CategoryResponse.builder()
                        .id(product.getCategory().getId())
                        .name(product.getCategory().getName())
                        .build())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Page<ProductResponse> getAllProduct(PageRequest request) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities()
                .forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        log.info("In method get product");
        return productRepository.findAll(request).map(product -> {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(()-> new RuntimeException("Category not found!"));
            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .thumbnail(product.getThumbnail())
                    .description(product.getDescription())
                    .createdAt(product.getCreatedAt())
                    .updatedAt(product.getUpdatedAt())
                    .category(CategoryResponse.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .build())
                    .build();
        });
    }

    @Override
    public ProductResponse updateProduct(long id, ProductCreationRequest request) {
        Product existingproduct = getById(id);
        Category existigncategory = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found!"));
        existingproduct.setName(request.getName());
        existingproduct.setPrice(request.getPrice());
        existingproduct.setDescription(request.getDescription());
        existingproduct.setThumbnail(request.getThumbnail());
        existingproduct.setCategory(existigncategory);
        productRepository.save(existingproduct);
        return ProductResponse.builder()
                .name(existingproduct.getName())
                .price(existingproduct.getPrice())
                .description(existingproduct.getDescription())
                .thumbnail(existingproduct.getThumbnail())
                .category(CategoryResponse.builder()
                        .id(existigncategory.getId())
                        .name(existigncategory.getName())
                        .build())
                .build();
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(value -> productRepository.delete(value));
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public ProductImage createProductImage(long productId, ProductImageCreationRequest request){
        log.warn("Product Id: " + String.valueOf(productId));
        Product existingProduct = getById(productId);

        ProductImage productImage = ProductImage.builder()
                .product(existingProduct)
                .imageUrl(request.getImageUr())
                .build();
        //Không cho thêm quá 5 ảnh trong 1 sản phẩm
        int size = productImageRepository.findAllByProductId(productId).size();
        if(size >= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT){
            throw new RuntimeException("Number of images must be <= "+ ProductImage.MAXIMUM_IMAGES_PER_PRODUCT);
        }
        return productImageRepository.save(productImage);
    }
}
