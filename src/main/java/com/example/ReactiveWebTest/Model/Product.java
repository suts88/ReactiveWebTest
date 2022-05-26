package com.example.ReactiveWebTest.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.Generated;

@Data
@Table("products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @Column("id")
    private Long id;

    @Column("product_number")
    private int productNumber;

    @Column("product_name")
    private String productName;

    @Column("price")
    private int price;


}
