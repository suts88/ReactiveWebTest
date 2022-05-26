package com.example.ReactiveWebTest.Model;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

    @Query(
            "SELECT * FROM Products u WHERE u.product_number = :productNumber"
    )
    public Mono<Product> findProductByProductNumber(int productNumber);

    @Query("DELETE From Products u WHERE u.product_number = :productNumber")
    public Mono<Product> deleteProductsByProductNumber(int productNumber);
}
