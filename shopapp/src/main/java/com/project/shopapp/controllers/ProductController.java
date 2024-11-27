package com.project.shopapp.controllers;


import com.project.shopapp.Services.ProductService;
import com.project.shopapp.dto.request.ApiResponse;
import com.project.shopapp.dto.request.ProductCreationRequest;
import com.project.shopapp.dto.request.ProductImageCreationRequest;
import com.project.shopapp.dto.response.ProductListResponse;
import com.project.shopapp.dto.response.ProductResponse;
import com.project.shopapp.models.ProductImage;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> crateProduct(@RequestBody @Valid ProductCreationRequest request){
            ProductResponse product = productService.createProduct(request);
            return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                            .result(product)
                    .build());
    }

    @PostMapping(value = "uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @PathVariable("id") long productId,
            @RequestParam("files") List<MultipartFile> files) {
        try {
            ProductResponse existingProduct = productService.getProductById(productId);
            files = files == null ? new ArrayList<>() : files;
            if(files.size() >= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT){
                return ResponseEntity.badRequest().body("You can only upload maximum 5 images");
            }
            List<ProductImage> productImages = new ArrayList<>();

            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }
                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is too large! Maximum file size is 10MB");
                }
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image");
                }

                String fileName = storeFile(file);
                ProductImage productImage = productService.createProductImage(
                        existingProduct.getId(),
                        ProductImageCreationRequest.builder()
                                .productId(existingProduct.getId())
                                .imageUr(fileName)
                                .build()
                );
                productImages.add(productImage);
            }

            return ResponseEntity.ok().body(productImages);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/images/{imageName}")
    public  ResponseEntity<?> viewImage(@PathVariable("imageName") String imageName){
        try {
            Path imagePath = Paths.get("uploads/" + imageName);
            UrlResource resource = new UrlResource(imagePath.toUri());
            if(resource.exists()){
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
            }///D:/ShopApp/shopapp/upload/4cf537b6-e871-476f-beb2-08b72fa7332d_Screenshot%202024-09-17%20000111
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private String storeFile(MultipartFile file)throws IOException{
        if(!isImageFile(file) || file.getOriginalFilename() != null){
            throw new IOException("Invalid image format");
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        //Đường dẫn thư mục muốn lưu file
        Path uploadDir = Paths.get("uploads");
        //Kiểm tra và tạo thư mục nếu nó không tồn tại
        if(!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
        //Đường dẫn đầy đủ đến file
        Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        //Sao chép file vào thư mục đích
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    private boolean isImageFile(MultipartFile file){
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ProductListResponse>> getProducts(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        PageRequest request = PageRequest.of(
                page, limit,
                Sort.by("createdAt").descending()
        );
        Page<ProductResponse> products = productService.getAllProduct(request);
        int totalPage = products.getTotalPages();
        List<ProductResponse> productList = products.getContent();
        ProductListResponse productListResponse = ProductListResponse.builder()
                .totalPages(totalPage)
                .productResponses(productList)
                .build();
        return ResponseEntity.ok(ApiResponse.<ProductListResponse>builder()
                        .message("get products here!")
                        .result(productListResponse)
                .build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable("id") long id){
        return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                        .code(1000)
                        .message("Selected!")
                        .result(productService.getProductById(id))
                .build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductById(@PathVariable("id") long id){
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                        .code(1000)
                        .message("Deleted!")
                .build());
    }
//    @PostMapping("/generateFakerProduct")
//    public ResponseEntity<String> generateFakerProduct(){
//        Faker faker = new Faker();
//        for(int i=0 ; i<=10000 ; i++){
//            String productName = faker.commerce().productName();
//            if(productService.existsByName(productName)){
//                continue;
//            }
//            ProductCreationRequest productCreationRequest = ProductCreationRequest.builder()
//                    .name(productName)
//                    .price(faker.number().numberBetween(10, 90000000))
//                    .description(faker.lorem().sentence())
//                    .thumbnail("")
//                    .categoryId((long)faker.number().numberBetween(3,5))
//                    .build();
//            productService.createProduct(productCreationRequest);
//        }
//        return ResponseEntity.ok("Fake products created successfully");
//    }
}
