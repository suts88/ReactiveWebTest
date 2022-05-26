package com.example.ReactiveWebTest.Services;

import com.example.ReactiveWebTest.Model.Product;
import com.example.ReactiveWebTest.Model.ProductRepository;
import com.example.ReactiveWebTest.Request.ProductRequest;
import com.example.ReactiveWebTest.Response.ProductResponse;
import lombok.AllArgsConstructor;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.Integer.parseInt;

@Service
@AllArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private DatabaseClient dbClient;


    private static ProductResponse ProductToProductResponse(Product product){
        return new ProductResponse(product.getProductNumber(), product.getProductName(), product.getPrice());
    }

    private static Product ProductRequestToProduct(ProductRequest productRequest){
        return new Product(null,productRequest.productNumber(), productRequest.productName(), productRequest.price());
    }

    public Flux<ProductResponse> getAllProducts(){
        return this.productRepository.findAll()
                .map(ProductService::ProductToProductResponse);
    }

    public Mono<ProductResponse> getProductByProductNumber(int productNumber){
        return this.productRepository.findProductByProductNumber(productNumber)
                .map(ProductService::ProductToProductResponse).log();
    }

    public Mono<ProductResponse> saveProduct(Mono<ProductRequest> productRequest){
        return productRequest.map(ProductService::ProductRequestToProduct)
                .flatMap(this.productRepository::save)
                .map(ProductService::ProductToProductResponse);
    }

    public Mono<ProductResponse> updateProduct(int productNumber, Mono<ProductRequest> productRequest){
       return  this.productRepository.findProductByProductNumber(productNumber)
               .flatMap( data -> productRequest.map(ProductService::ProductRequestToProduct))
               .flatMap(this.productRepository::save)
               .map(ProductService::ProductToProductResponse);
    }

    public Mono<ProductResponse> deleteProduct(int productNumber){
        return this.productRepository.deleteProductsByProductNumber(productNumber)
                .map(ProductService::ProductToProductResponse);
    }
}
