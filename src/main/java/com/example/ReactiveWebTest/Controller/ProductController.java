package com.example.ReactiveWebTest.Controller;

import com.example.ReactiveWebTest.Request.ProductRequest;
import com.example.ReactiveWebTest.Response.ProductResponse;
import com.example.ReactiveWebTest.Services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public Flux<ProductResponse> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @GetMapping("/{productNumber}")
    public Mono<ProductResponse> getProductByNumber(@PathVariable int productNumber) {
        return this.productService.getProductByProductNumber(productNumber);
    }

    @PostMapping
    public Mono<ResponseEntity<ProductResponse>> saveProduct(@RequestBody Mono<ProductRequest> productRequest) {
        return this.productService.saveProduct(productRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{productNumber}")
    public Mono<ResponseEntity<ProductResponse>> updateProduct(@PathVariable int productNumber, @RequestBody Mono<ProductRequest> productRequest) {
        return this.productService.updateProduct(productNumber, productRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{productNumber}")
    public Mono<ResponseEntity<ProductResponse>> deleteProduct(@PathVariable int productNumber) {
        return this.productService.deleteProduct(productNumber)
                .map(ResponseEntity::ok);

    }
}
